package com.jifan.pssmis.foundation.util;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.message.SysMessage;






/**
 * jifan
 */
public class CompareUtil {
	private static Log log = LogFactory.getLog(CompareUtil.class);
	/*
	public static final String LT = "\\<";
	public static final String LE = "\\<=";
	public static final String GT = "\\>";
	public static final String GE = "\\>=";
	public static final String EL = "==";
	public static final String NE = "!=";*/
	
	//匹配整型比较表达式
	public static final String INT_COM_REG = "^\\d+\\s*(\\<|(\\<\\=)|\\>|(\\>\\=)|(\\=\\=)|(\\!\\=))\\s*\\d+$";				//可含有空格
	//匹配字符串比较表达式
	public static final String STR_COM_REG = "(^'\\w+')\\s*(\\<|(\\<\\=)|\\>|(\\>\\=)|(\\=\\=)|(\\!\\=))\\s*('\\w+'$)";		//可含有空格
	//匹配比较表达式(包含整型、字符串、引用),//可含有空格
	public static final String COM_REG = "('\\w+'|\\d+|[a-zA-Z0-9_.\\[\\]]+)\\s*(\\<|(\\<\\=)|\\>|(\\>\\=)|(\\=\\=)|(\\!\\=))\\s*('\\w+'|\\d+|[a-zA-Z0-9_.\\[\\]]+)";
	
	/**
	 * 
	 *功能描述：计算表达式的值
	 * @param expr
	 * @return
	 * @throws BizException
	 * * @throws NoSuchMethodException
	 * * @throws IllegalAccessException
	 * * @throws InvocationTargetException
	 */
	public static boolean calculateCompareValue(String expr) throws BizException,
																NoSuchMethodException,
																IllegalAccessException,
																InvocationTargetException {
		boolean rtnVal = false;
		if(log.isDebugEnabled()) {
			log.debug("==========比较器的表达式：expr =" + expr);
		}
		int comVal = compare(expr);
		
		String operator = getOperator(expr).trim();
		
		if(operator.equals("<")) {
			switch(comVal) {
			case 0 : 
				rtnVal = false;
				break;
			case 1 : 
				rtnVal = false;
				break;
			case -1 : 
				rtnVal = true;
				break;
			}
		}else if (operator.equals("<=")) {
			switch(comVal) {
			case 0 : 
				rtnVal = true;
				break;
			case 1 : 
				rtnVal = false;
				break;
			case -1 : 
				rtnVal = true;
				break;
			}
		}else if (operator.equals(">")) {
			switch(comVal) {
			case 0 : 
				rtnVal = false;
				break;
			case 1 : 
				rtnVal = false;
				break;
			case -1 : 
				rtnVal = false;
				break;
			}
		}else if (operator.equals(">=")) {
			switch(comVal) {
			case 0 : 
				rtnVal = true;
				break;
			case 1 : 
				rtnVal = true;
				break;
			case -1 : 
				rtnVal = false;
				break;
			}
		}else if (operator.equals("==")) {
			switch(comVal) {
			case 0 : 
				rtnVal = true;
				break;
			case 1 : 
				rtnVal = false;
				break;
			case -1 : 
				rtnVal = false;
				break;
			}
		}else if (operator.equals("!=")) {
			switch(comVal) {
			case 0 : 
				rtnVal = false;
				break;
			case 1 : 
				rtnVal = true;
				break;
			case -1 : 
				rtnVal = true;
				break;
			}
		}
		return rtnVal;
	}
	/**
	 * 
	 *功能描述：计算表达式的值
	 * @param expr
	 * @return	返回值只有三种情况：
	 * 		-1(前置操作数 小于 后置操作数)，
	 * 		0(前置操作数 等于于 后置操作数),
	 * 		1(前置操作数 大于 后置操作数)
	 * @throws BizException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static int compare(String expr) throws BizException,
											NoSuchMethodException,
											IllegalAccessException,
											InvocationTargetException {
		if(log.isDebugEnabled()) {
			log.debug("===========比较表达式替换前：" + expr);
		}
		/*****************************如果存在引用表达式，则先计算比较表达式中的引用表达式的值************************************************/
		StringBuffer rtnVal = new StringBuffer();
		Pattern p = Pattern.compile(ExpressionEvaluatorUtil.REF_REG);
		Matcher m = p.matcher(expr);
		while (m.find()) {
			String foundStr = m.group();
			Object exprVal = ExpressionEvaluatorUtil.getRegExprValue(foundStr);
			if(exprVal instanceof String) {
				exprVal = "'" + exprVal + "'";
			}
			m.appendReplacement(rtnVal,String.valueOf(exprVal));
		}
		m.appendTail(rtnVal);
		/*****************************************************************************/
		expr = rtnVal.toString();
		
		if(log.isDebugEnabled()) {
			log.debug("===========比较表达式替换后：" + expr);
		}
		String operator = getOperator(expr).trim();
		String opeReg = "";
		
		//定制匹配操作符的正则表达式
		if(operator.length() ==1) {
			opeReg = "\\" + operator;
		}else if (operator.length() == 2) {
			opeReg = "\\" + operator.charAt(0) + "\\" + operator.charAt(1);
		}else {
			throw new BizException (new SysMessage("比较表达式的操作符错误！operator is " + operator));
		}
		
		if(log.isDebugEnabled()) {
			log.debug("==========操作符匹配的正则表达式  opeReg is " + opeReg);
		}
		
		String [] vars = expr.split(opeReg);
		String preVar = vars [0].trim();
		String subVar = vars [1].trim();
		
		if(log.isDebugEnabled()) {
			log.debug("==========preVar is " + preVar);
			log.debug("==========subVar is " + subVar);
		}
		
		int comVal = 0;	//比较值
		
		//判断是否为整型比较表达式
		if(isIntCompareExpression(expr)) {
			if(log.isDebugEnabled()) {
				log.debug("=============isIntCompareExpression=" + expr);
				log.debug("=============isIntCompareExpression   value=" + Integer.valueOf(preVar).compareTo(Integer.valueOf(subVar)));
			}
			comVal = Integer.valueOf(preVar).compareTo(Integer.valueOf(subVar));
		}else if(isStrCompareExpression(expr)) {
			if(log.isDebugEnabled()) {
				log.debug("=============isStrCompareExpression=" + expr);
				log.debug("=============isStrCompareExpression   value=" + preVar.compareTo(subVar));
			}
			comVal = preVar.compareTo(subVar);
		}else {
			
			throw new BizException (new SysMessage("错误的比较表达式！目前只支持 <整型> 和 <字符串型> 的表达式。"));
		}
		//整理比较值
		if(comVal > 0) {
			comVal = 1;
		}else if(comVal == 0) {
			comVal = 0;
		}else {
			comVal = -1;
		}
		return comVal;
	}
	/**
	 * 
	 *功能描述：获取比较表达式的操作符
	 * @param str	表达式字符串
	 * @return	操作符
	 */
	private static String getOperator(String str) {
		//匹配比较表达式操作符
		String patternStr = "(\\<|(\\<\\=)|\\>|(\\>\\=)|(\\=\\=)|(\\!\\=))";	
		Pattern p = Pattern.compile(patternStr);
		Matcher m = p.matcher(str);
		while(m.find()) {
			String s = m.group();
			if(log.isDebugEnabled()) {
				log.debug("=======比较表达式的operator is " + s);
			}
			return s;
		}
		return "";
	}
	/**
	 * 
	 *功能描述：判断表达式是否为 ‘比较表达式’
	 * @param expr
	 * @return
	 */
	public static boolean isCompareExpression(String expr) {
		Pattern p = Pattern.compile(COM_REG);
		Matcher m = p.matcher(expr);
		while (m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 *功能描述：判断表达式是否为 ‘字符串型比较表达式’
	 * @param expr	表达式字符串
	 * @return
	 */
	private static boolean isStrCompareExpression(String expr) {
		//匹配字符串型比较表达式
		Pattern p = Pattern.compile(STR_COM_REG);
		Matcher m = p.matcher(expr);
		while (m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 *功能描述：判断表达式是否为‘整型比较表达式’
	 * @param expr
	 * @return
	 */
	private static boolean isIntCompareExpression(String expr) {
		//匹配整型比较表达式
		Pattern p = Pattern.compile(INT_COM_REG);
		Matcher m = p.matcher(expr);
		while (m.find()) {
			return true;
		}
		return false;
	}
}
