package com.jifan.pssmis.foundation.util;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jifan.pssmis.foundation.exception.BizException;








/**
 * 逻辑计算工具类
 * @author jifan
 *
 */
public class LogicUtil {
	private static final Log log = LogFactory.getLog(LogicUtil.class);
	//逻辑关系表达式，目前只支持一元运算‘!’
	private static final String LOGIC_PATTERN = "\\s*!\\s*(true|false|[a-zA-Z0-9_.\\[\\]])";
	
	/**
	 * 
	 *功能描述：计算逻辑表达式的值，如果计算结果不为boolean 类型，则返回false;
	 * @param expr
	 * @return
	 * @throws BizException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws cn.ccb.foundation.common.exception.BizException 
	 */
	public static boolean calculateLogicValue(String expr) throws BizException,
																NoSuchMethodException,
																IllegalAccessException,
																InvocationTargetException {
		boolean rtnVal = false;
		if(log.isDebugEnabled()) {
			log.debug("=============逻辑表达式expr=" + expr);
		}
		if(expr == null) {
			return false;
		}
		if(isLogicExpression(expr)) {
			expr = StringUtils.replace(expr,"!","");
			
			//只是针对带引用表达式的逻辑表达式，例如：(!bb.name 或 !true 或 !false)
			Object exprVal = ExpressionEvaluatorUtil.getRegExprValue(expr);
			if(exprVal instanceof Boolean) {
				rtnVal = !((Boolean)exprVal).booleanValue();
			}
		}
		if(log.isDebugEnabled()) {
			log.debug("===========逻辑表达式的值:" + rtnVal);
		}
		return rtnVal;
	}
	/**
	 * 
	 *功能描述：判断表达式是否为一元逻辑表达式
	 * @param expr
	 * @return
	 */
	public static boolean isLogicExpression(String expr) {
		if(expr != null) {
			Pattern p = Pattern.compile(LOGIC_PATTERN);
			Matcher m = p.matcher(expr);
			while (m.find()) {
				return true;
			}
		}
		return false;
	}
}
