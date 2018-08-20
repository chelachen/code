package com.jifan.pssmis.foundation.util;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.message.SysMessage;






/**
 * 表达式计算类
 * @author jifan
 *
 */
public class ExpressionEvaluatorUtil  {
	private static Log log = LogFactory.getLog(ExpressionEvaluatorUtil.class);
	
	public static final String REF_REG = "(@?[a-zA-Z0-9_.\\[\\]]+|'\\w+')";	//含有引用表达式的匹配
	
	//综合表达式的匹配方式(含有引用表达式、复合运算表达式、比较表达式)
	public static final String REG = "\\$\\{\\s*(" + REF_REG + "|" + MathUtil.COMPOUND_REG + "|" + CompareUtil.COM_REG + ")\\s*\\}";
	
	/**
	 * 
	 *功能描述：适合混合表达式(表达式和字符串混合时)使用.
	 * @param str	例如：value="nihao${bb.address.name}${1+1}"
	 * @return	结果返回字符串
	 */
	public static String getValue(String str) {
		if(log.isDebugEnabled()) {
			log.debug("============getValue()中参数:str=" + str);
		}
		if(str == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		
		Pattern p = Pattern.compile(REG);
		Matcher m = p.matcher(str);
		try {
			while (m.find()) {
				String foundStr = m.group();
				String retValue = getExprValue(foundStr) == null ? "" : getExprValue(foundStr).toString();
				m.appendReplacement(sb, retValue);
			}
			m.appendTail(sb);
		} catch(Exception ex){
			log.error("求解混合表达式：" + str + "时出错，原因：" + ex);
		}
		return sb.toString();
	}
	/**
	 * 核心
	 *功能描述：求解表达式的值，适用于纯表达式的情况
	 * @param expr	例如：value="${***}"
	 * @return
	 * @throws Exception
	 */
	public static Object getExprValue(String expr) {
		if(log.isDebugEnabled()) {
			log.debug("============getExprValue()中的表达式参数:expr=" + expr);
		}
		if(expr == null) {
			return null;
		}
		Object rtnObj = null;
		try {
			//是否含有表达式
			if(hasExpression(expr)) {
				String exprVal = expr.substring(expr.indexOf("${") + 2,expr.indexOf("}"));
				
				/*******************以下的顺序有讲究*************************************/
				
				//复合算术表达式
				if(MathUtil.isCompoundExpression(exprVal)) {
					return MathUtil.calculateExprValue(exprVal);
				}
				//比较表达式
				if(CompareUtil.isCompareExpression(exprVal)) {
					return String.valueOf(CompareUtil.calculateCompareValue(exprVal));
				}
				/**********hongchang 20070911 start 增加逻辑表达式计算开始　*****************/
				//逻辑表达式
				if(LogicUtil.isLogicExpression(exprVal)) {
					return Boolean.valueOf(LogicUtil.calculateLogicValue(exprVal));
				}
				/**********hongchang 20070911 start 增加逻辑表达式计算结束　*****************/
				//引用表达式
				if(isRefExpression(exprVal)) {
					return getRegExprValue(exprVal);
				}
				//普通表达式,放在最后，因为其判断比较粗糙。
				if(MathUtil.isPlainExpression(exprVal)) {
					return MathUtil.getPlainExprVlaue(exprVal);
				}
				else {
					return "不能解析的表达式：" + expr;
				}
			}else {
				//不含有表达式，则返回本身
				return (expr);
			}
		} catch(Exception ex) {
			log.error("求解表达式：" + expr + "时出错，原因：" + ex);
		}
		return rtnObj;
	}
	/**
	 * 
	 *功能描述：求解引用表达式的值。
	 *			目前引用表达式支持三种类型的‘引用常量’：
	 *			1、整型常量${23}；
	 *			2、布尔常量:${true}；
	 *			3、字符串常量:${'abc'};
	 * @param expr	引用表达式中的字符串常量用单引号(');
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getRegExprValue(String expr) throws BizException, NoSuchMethodException ,
															IllegalAccessException,
															InvocationTargetException {
		if(log.isDebugEnabled()) {
			log.debug("引用表达式： expr=" + expr);
		}
		if(expr == null) {
			return null;
		}
		Object rtnObj = null;
		
		/************************报表自定义常量表达式*******************************************/
		if(expr.equals("@nowdate")){
			//日期 常量表达式
			rtnObj = DateUtil.getStrNowDateShort();					
		}else if(expr.equals("@fiscalmonth")){
			//会计月份 常量表达式
			rtnObj = DateUtil.getFiscalmonth();
		/************************报表自定义常量表达式*******************************************/
			
		}else if(expr.indexOf(".") > 0) {
			//求解bean属性值
			String beanName = expr.substring(0,expr.indexOf("."));
			String propertyName = expr.substring(expr.indexOf(".") + 1,expr.length());
			
			/*if(log.isDebugEnabled()) {
				log.debug("=======beanName=" + beanName);
				log.debug("=======propertyName=" + propertyName);
			}*/
			
			Object bean = ContextUtil.getManagedBean(beanName);
			if(bean == null) {
				log.error("配置文件中不存在名称为：" + beanName + " 的BackingBean ！");
				throw new BizException(new SysMessage("配置文件中不存在名称为：" + beanName + " 的BackingBean ！"));
			}
			rtnObj = PropertyUtils.getProperty(bean,propertyName);
		}else if(expr.indexOf("[") > 0) {
			//求解数组类型的表达式,扩展中...
		}else if(isRefConstant(expr)) {
			
			//先判断引用表达式的形式是否为字符串型引用常量
			if(isStringRefConstant(expr)) {
				rtnObj = expr.replaceAll("'","");
			}else if(isIntegerRefConstant(expr)) {
				rtnObj = Integer.valueOf(expr.trim());
			}else if(isBooleanRefConstant(expr)) {
				rtnObj = Boolean.valueOf(expr.trim());
			}else {
				throw new BizException(new SysMessage("尚未支持的引用常量表达式！"));
			}
		} else {
			//从request,session,application scope 中查找值
			rtnObj = getAttrValue(expr);
		}
		return rtnObj;
	}
	/**
	 * 
	 *功能描述：判断是否为引用表达式，
	 * @param expr	例如："bb.age" 
	 * 					或 "bb.address.name"
	 * 					或 自定义常量表达式
	 * 					或 引用常量
	 * @return	
	 */
	public static boolean isRefExpression(String expr) {
		Pattern p = Pattern.compile(REF_REG);
		Matcher m = p.matcher(expr);
		while (m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 *功能描述：判断是否为表达式
	 * @param expr
	 * @return
	 */
	public static boolean hasExpression(String expr) {
		if(expr == null) {
			return false;
		}
		return expr.indexOf("${") == 0 && expr.indexOf("}") > expr.indexOf("${");
	}
	/**
	 * 
	 *功能描述：依次从request、session、application范围中查找并返回参数值
	 * @param param
	 * @return
	 */
	private static Object getAttrValue(String param) {
		//先从request scope 中查找
		Object obj = ContextUtil.getExternalContext().getRequestMap().get(param);
		if(obj == null) {
			//再从session scope 中查找
			ContextUtil.getExternalContext().getSessionMap().get(param);
		}
		if(obj == null) {
			//最后从application scope 中查找
			ContextUtil.getExternalContext().getApplicationMap().get(param);
		}
		return obj;
	}
	/**
	 * 
	 *功能描述：判断是否为引用常量
	 * @param expr
	 * @return
	 */
	public static boolean isRefConstant(String expr) {
		final String REF_CON_REG = "(('\\w+')|\\d+|(true|false))";
		Pattern p = Pattern.compile(REF_CON_REG);
		Matcher m = p.matcher(expr);
		while(m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 *功能描述：判断是否为字符串型引用常量
	 * @param expr
	 * @return
	 */
	private static boolean isStringRefConstant(String expr) {
		final String STRING_REF_CON_REG = "'\\w+'";
		Pattern p = Pattern.compile(STRING_REF_CON_REG);
		Matcher m = p.matcher(expr);
		while(m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 *功能描述：判断是否为整型引用常量
	 * @param expr
	 * @return
	 */
	private static boolean isIntegerRefConstant(String expr) {
		final String INTEGER_REF_CON_REG = "\\d+";
		Pattern p = Pattern.compile(INTEGER_REF_CON_REG);
		Matcher m = p.matcher(expr);
		while(m.find()) {
			return true;
		}
		return false;
		
	}
	/**
	 * 
	 *功能描述：判断是否为布尔型引用常量
	 * @param expr
	 * @return
	 */
	private static boolean isBooleanRefConstant(String expr) {
		final String BOOLEAN_REF_CON_REG = "true|false";
		Pattern p = Pattern.compile(BOOLEAN_REF_CON_REG);
		Matcher m = p.matcher(expr);
		while(m.find()) {
			return true;
		}
		return false;
	}
}
