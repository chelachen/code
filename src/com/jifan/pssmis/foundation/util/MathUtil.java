package com.jifan.pssmis.foundation.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jifan.pssmis.foundation.contants.DataCodeContants;

/**
 * 数值类计算公共类
 * 
 * @author jifan
 */
public class MathUtil {
	
	private static final char ADD = '+';
	private static final char MINUS = '-';
	private static final char MUL = '*';
	private static final char DIV = '/';
	private static final char MOD = '%';
	//给定的货币输出格式
	private final static DecimalFormat df = new DecimalFormat("###,##0.00");

	// 匹配单一运算表达式
	public static final String SIMPLE_REG = "^\\d+\\s*[\\+\\-\\*/%]\\s*\\d+$"; // 匹配简单算术表达式,例如："14
																				// +
																				// 55"（以数字开始,中间含有运算符,可含有空格）
	// 匹配复合运算表达式
	public static final String COMPOUND_REG = "\\d+(\\s*[\\+\\-\\*/%]\\s*\\d+)+"; // 可含有空格

	/**
	 * 用于数值的计算 加
	 * 
	 * @param a
	 *            double 数值 a
	 * @param b
	 *            double 数值 b
	 * @return double eg:
	 */
	public static double BDadd(double a, double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.add(b2);
		return b1.doubleValue();
	}

	/**
	 * 用于数值的计算 加 先转换为Bigdecimal后运算
	 * 
	 * @param a
	 *            Double 数值 a
	 * @param b
	 *            Double 数值 b
	 * @param scale
	 *            精确到小数点的位数
	 * @return double
	 */
	public static double BDadd(double a, double b, int scale) {
		return round(BDadd(a, b), scale);
	}

	/**
	 * 对两数值进行相加;scale=-1时,不进行精度运算
	 * 
	 * @param a
	 *            Double 数值 a
	 * @param b
	 *            Double 数值 b
	 * @param scale
	 *            int 精度值
	 * @return Double
	 */
	public static Double BDadd(Double a, Double b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return Double.valueOf(round(scale,
				new BigDecimal(BDadd(a.doubleValue(), b.doubleValue())))
				.doubleValue()
				+ "");
	}
	
	/**
	 * 次方计算，a的b次方
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double pow(Double a, Double b){
		return Math.pow(a, b);
	}

	/**
	 * 对两数值进行相加;scale=-1时,不进行精度运算
	 * 
	 * @param a
	 *            BigDecimal
	 * @param b
	 *            BigDecimal
	 * @param scale
	 *            int
	 * @return BigDecimal
	 */
	public static BigDecimal BDadd(BigDecimal a, BigDecimal b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return round(scale, a.add(b));
	}

	/**
	 * 用于数值的计算 乘 先转换为Bigdecimal后运算
	 * 
	 * @param a
	 *            Double 数值 a
	 * @param b
	 *            Double 数值 b
	 * @return double
	 */
	public static double BDmultiply(double a, double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.multiply(b2);
		return b1.doubleValue();
	}

	/**
	 * 用于数值的计算 乘 先转换为Bigdecimal后运算
	 * 
	 * @param a
	 *            double
	 * @param b
	 *            double
	 * @param scale
	 *            精确到小数点的位数
	 * @return double eg:
	 * 
	 */
	public static double BDmultiply(double a, double b, int scale) {
		return round(BDmultiply(a, b), scale);
	}

	/**
	 * 对两数值进行相乘;scale=-1时,不进行精度运算
	 * 
	 * @param a
	 *            Double 数值 a
	 * @param b
	 *            Double 数值 b
	 * @param scale
	 *            int 精度
	 * @return Double eg:
	 * 
	 */
	public static Double BDmultiply(Double a, Double b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return Double.valueOf(round(scale,
				new BigDecimal(BDmultiply(a.doubleValue(), b.doubleValue())))
				.doubleValue()
				+ "");
	}

	/**
	 * 对两数值进行相乘;scale=-1时,不进行精度运算
	 * 
	 * @param a
	 *            BigDecimal 数值 a
	 * @param b
	 *            BigDecimal 数值 b
	 * @param scale
	 *            int 精度
	 * @return BigDecimal eg:
	 */
	public static BigDecimal BDmultiply(BigDecimal a, BigDecimal b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return round(scale, a.multiply(b));
	}

	/**
	 * 用于数值的计算 减 return = a - b 先转换为Bigdecimal后运算
	 * 
	 * @param a
	 *            double
	 * @param b
	 *            double
	 * @return double eg:
	 */
	public static double BDsubtract(double a, double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.subtract(b2);
		return b1.doubleValue();
	}

	/**
	 * 用于数值的计算 减 return = a - b 先转换为Bigdecimal后运算
	 * 
	 * @param a
	 *            double
	 * @param b
	 *            double
	 * @param scale
	 *            精确到小数点的位数
	 * @return double eg:
	 */
	public static double BDsubtract(double a, double b, int scale) {
		return round(BDsubtract(a, b), scale);
	}

	/**
	 * 对两数值进行相减(a-b);scale=-1时,不进行精度运算
	 * 
	 * @param a
	 *            Double 数值 a
	 * @param b
	 *            Double 数值 b
	 * @param scale
	 *            int 精度
	 * @return Double eg:
	 */
	public static Double BDsubtract(Double a, Double b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return Double.valueOf(round(scale,
				new BigDecimal(BDsubtract(a.doubleValue(), b.doubleValue())))
				.doubleValue()
				+ "");
	}

	/**
	 * 对两数值进行相减(a-b);scale=-1时,不进行精度运算
	 * 
	 * @param a
	 *            BigDecimal 数值 a
	 * @param b
	 *            BigDecimal 数值 b
	 * @param scale
	 *            int 精度
	 * @return BigDecimal eg:
	 */
	public static BigDecimal BDsubtract(BigDecimal a, BigDecimal b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return round(scale, a.subtract(b));
	}

	/**
	 * 用于数值的计算 除 return= a / b 先转换为Bigdecimal后运算
	 * 
	 * @param a
	 *            double
	 * @param b
	 *            double
	 * @param scale
	 *            精确到小数点的位数
	 * @return double eg:
	 */
	public static double BDdivide(double a, double b, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
		return b1.doubleValue();
	}

	/**
	 * 对两数值进行相除a/b;
	 * 
	 * @param a
	 *            Double 数值 a
	 * @param b
	 *            Double 数值 b
	 * @param scale
	 *            int 精度
	 * @return Double eg:
	 */
	public static Double BDdivide(Double a, Double b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return Double.valueOf(BDdivide(a.doubleValue(), b.doubleValue(), scale)
				+ "");
	}

	/**
	 * 对两数值进行相除a/b;
	 * 
	 * @param a
	 *            BigDecimal 数值 a
	 * @param b
	 *            BigDecimal 数值 b
	 * @param scale
	 *            int 精度
	 * @return BigDecimal eg:
	 */
	public static BigDecimal BDdivide(BigDecimal a, BigDecimal b, int scale) {
		if (a == null || b == null)
			throw new IllegalArgumentException("运算参数为空!");
		return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 用于Double数据的精度运算
	 * 
	 * @param db
	 * @param scale
	 * @param roundmode
	 *            使用BigDecimal的处理方式 例如BigDecimal.ROUND_FLOOR表示向下取整
	 * @return eg:
	 */
	public static Double round(Double db, int scale, int roundmode) {
		if (db == null)
			throw new IllegalArgumentException("运算参数为空!");
		BigDecimal dbr = new BigDecimal(db.doubleValue());
		BigDecimal base = new BigDecimal("1");
		dbr = dbr.divide(base, scale, roundmode);
		return new Double(dbr.doubleValue());

	}

	/**
	 * 用于double数据的四舍五入
	 * 
	 * @param d
	 *            double
	 * @param scale
	 *            int
	 * @return double eg:
	 */
	public static double round(double d, int scale) {
		return round(new BigDecimal(Double.toString(d)), scale);
	}

	/**
	 * 用于double数据的四舍五入
	 * 
	 * @param bd
	 *            BigDecimal
	 * @param scale
	 *            int
	 * @return double eg:
	 */
	private static double round(BigDecimal bd, int scale) {
		BigDecimal base = new BigDecimal("1");
		bd = bd.divide(base, scale, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * 用于BigDecimal数据的四舍五入
	 * 
	 * @param scale
	 *            精度,-1时返回原值
	 * @param bd
	 *            传进的大数据数值
	 * @return 大数据数值 eg:
	 * 
	 */
	public static BigDecimal round(int scale, BigDecimal bd) {
		if (scale < 0)
			return bd;
		return new BigDecimal(round(bd, scale) + "");
	}

	/**
	 * 用于float数据的四舍五入
	 * 
	 * @param d
	 *            float
	 * @param scale
	 *            int
	 * @return float eg:
	 */
	public static float round(float d, int scale) {
		BigDecimal b1 = new BigDecimal("" + d);
		BigDecimal base = new BigDecimal("1");
		b1 = b1.divide(base, scale, BigDecimal.ROUND_HALF_UP);
		return b1.floatValue();
	}
	
	/**
	 * 进位运算，小数进为整数
	 * 1->1
	 * 1.1->2
	 * 2.6->3
	 * @param d
	 * @return
	 */
	public static double ceil(Double d){
		return Math.ceil(d);
	}

	/**
	 * 比较两个值是否相等
	 * 
	 * @param double,double
	 * @return int 如果d1-d2的值小于10的-6次方则认为两个数相等 返回值 0 d1=d2 1 d1>d2 -1 d1<d2
	 */
	public static int compareAmount(double d1, double d2) {
		if (d1 == d2)
			return 0;
		double result = BDsubtract(d1, d2, 10);
		if (Math.abs(result) <= 1.0E-6)
			return 0;
		if (result < 0)
			return -1;
		return 1;
	}
	
	/**
	 * 
	 *功能描述：	对表达式进行‘加’运算
	 * @param addexpr	例如： "1+5+5" 
	 * @return
	 */
	private static String add(String addexpr) {
		int rtnval = 0;
		String [] nums = addexpr.split("\\+");
		for(int i = 0;i < nums.length;i ++) {
			String num = nums[i].trim();
			rtnval += Integer.parseInt(num);
		}
		return String.valueOf(rtnval);
	}
	/**
	 * 
	 *功能描述：	对表达式进行‘减’运算
	 * @param minisexpr	例如： "1-5-5" 
	 * @return
	 */
	private static String minus(String minisexpr) {
		int rtnval = 0;
		String [] nums = minisexpr.split("\\-");
		for(int i = 0;i < nums.length;i ++) {
			String num = nums[i].trim();
			rtnval -= Integer.parseInt(num);
			if(i == 0) {
				rtnval = - rtnval;
			}
		}
		return String.valueOf(rtnval);
	}
	/**
	 * 
	 *功能描述：	对表达式进行‘乘’运算
	 * @param mulexpr	例如： "1*5*5" 
	 * @return
	 */
	private static String mul(String mulexpr) {
		int rtnval = 1;
		String [] nums = mulexpr.split("\\*");
		for(int i = 0;i < nums.length;i ++) {
			String num = nums[i].trim();
			rtnval *= Integer.parseInt(num);
		}
		return String.valueOf(rtnval);
	}
	/**
	 * 
	 *功能描述：	对表达式进行‘除’运算
	 * @param divexpr	例如： "1/5/5" 
	 * @return
	 */
	private static String div(String divexpr) {
		int rtnval = 1;
		String [] nums = divexpr.split("/");
		for(int i = 0;i < nums.length;i ++) {
			String num = nums[i].trim();
			if(i == 0) {
				rtnval = Integer.parseInt(num);
			}else {
				rtnval /= Integer.parseInt(num);
			}
		}
		return String.valueOf(rtnval);
	}

	/**
	 * 
	 * 功能描述： 对表达式进行‘取模’运算
	 * 
	 * @param modexpr
	 *            例如： "1%5%5"
	 * @return
	 */
	private static String mod(String modexpr) {
		int rtnval = 1;
		String[] nums = modexpr.split("%");
		for (int i = 0; i < nums.length; i++) {
			String num = nums[i].trim();
			if (i == 0) {
				rtnval = Integer.parseInt(num);
			} else {
				rtnval %= Integer.parseInt(num);
			}
		}
		return String.valueOf(rtnval);
	}

	/**
	 * 计算复合表达式的值 expr 例如："1+5+5*5-3"
	 */
	public static String calculateExprValue(String expr) {
		StringBuffer rtnStr = new StringBuffer();



		StringBuffer tempExpr = new StringBuffer(expr);
		if (isCompoundExpression(tempExpr.toString())) {
			// 复合算术表达式运算
			Pattern p = Pattern.compile(SIMPLE_REG);
			Matcher m = p.matcher(tempExpr);
			while (m.find()) {
				String s = m.group();
				String val = "";
				char operator = getOperator(s);
				switch (operator) {
				case ADD:
					val = add(s);
					break;
				case MINUS:
					val = minus(s);
					break;
				case MUL:
					val = mul(s);
					break;
				case DIV:
					val = div(s);
					break;
				case MOD:
					val = mod(s);
					break;
				default:
					val = s;
					break;
				}

				m.appendReplacement(rtnStr, val);
			}
			m.appendTail(rtnStr);
			tempExpr = rtnStr;
			// 采用递归
			return calculateExprValue(tempExpr.toString());
		}
		return tempExpr.toString();
	}

	/**
	 * 
	 * 功能描述：从字符串中提取操作符
	 * 
	 * @param str
	 * @return
	 */
	private static char getOperator(String str) {
		String patternStr = "[\\+\\-\\*/%]"; // 匹配复合算术表达式操作符
		Pattern p = Pattern.compile(patternStr);
		Matcher m = p.matcher(str);
		while (m.find()) {
			String s = m.group();
			return s.charAt(0);
		}
		return 0;
	}

	/**
	 * 判断是否为复合算术表式
	 */
	public static boolean isCompoundExpression(String expr) {
		Pattern p = Pattern.compile(COMPOUND_REG);
		Matcher m = p.matcher(expr);
		while (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 功能描述：返回普通表达式
	 * 
	 * @param expr
	 * @return
	 */
	public static String getPlainExprVlaue(String expr) {
		return expr.replaceAll("\\+", "");
	}

	/**
	 * 
	 * 功能描述：判断是否为普通表达式(字符串连接)
	 * 
	 * @param expr
	 * @return
	 */
	public static boolean isPlainExpression(String expr) {
		final String STR_CONCAT_REG = "'\\w+'(\\s*\\+\\s*'\\w+')+";
		Pattern p = Pattern.compile(STR_CONCAT_REG);
		Matcher m = p.matcher(expr);
		while (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 用于数值的计算 加 先转换为Bigdecimal后运算
	 */
	public static double add(double a, double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.add(b2);
		return b1.doubleValue();
	}

	/**
	 * 用于数值的计算 乘 先转换为Bigdecimal后运算
	 */
	public static double multiply(double a, double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.multiply(b2);
		return b1.doubleValue();
	}

	/**
	 * 用于数值的计算 减 return = a - b 先转换为Bigdecimal后运算
	 */
	public static double minus(double a, double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.subtract(b2);
		return b1.doubleValue();
	}

	/**
	 * 用于数值的计算 除 return= a / b 先转换为Bigdecimal后运算 只适于可以整除的应用，
	 */
	public static double divide(double a, double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.divide(b2, BigDecimal.ROUND_UNNECESSARY);
		return b1.doubleValue();
	}

	/**
	 * 用于数值的计算 除 return= a / b 先转换为Bigdecimal后运算
	 * 
	 * @param scale
	 *            精确到小数点的位数
	 */
	public static double divide(double a, double b, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		b1 = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
		return b1.doubleValue();
	}

	public static BigDecimal formatToMoney(BigDecimal value) {
		if (value == null) {
			return null;
		}
		// return
		// value.divide(BigDecimal.valueOf(10000),2,BigDecimal.ROUND_HALF_UP);
		return value.divide(BigDecimal.valueOf(1), 2, BigDecimal.ROUND_HALF_UP);

	}

	/**
	 * 
	 * 功能描述：将给定double 类型的数据格式化成货币类型
	 * 
	 * @param d
	 * @return
	 */
	public static String formatToMoney(double d) {
		try {
			return df.format(d);
		} catch (Exception ex) {
			return String.valueOf(d);
		}
	}
	/**
	 * 
	 * 判断是不是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		Pattern pattern = Pattern.compile("[-\\+]?[0-9]+\\.?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 格式化总金额的逻辑工具方法
	 * 
	 * @param rule
	 *            格式化规则
	 * @param scale``````````````
	 *            格式化小数位
	 * @return
	 */
	public static BigDecimal formateTotalMoneyUtil(Integer rule, Integer scale,BigDecimal seriesPrice) {
		// 四舍五入
		if (DataCodeContants.BGN_ATTR_36_UP != null && scale != null
				&& DataCodeContants.BGN_ATTR_36_UP.equals(rule)) {
			return seriesPrice.setScale(scale, BigDecimal.ROUND_HALF_UP);
		}// 直接舍去
		else if (DataCodeContants.BGN_ATTR_36_DOWN != null && scale != null
				&& DataCodeContants.BGN_ATTR_36_DOWN.equals(rule)) {
			return seriesPrice.setScale(scale, BigDecimal.ROUND_DOWN);
		}// 默认四舍五入，取整
		else
			return seriesPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 获取对应值范围的随机数
	 * @param digit
	 * @return
	 */
	public static String getRandomNum(long min,long max){
		long  temp=Math.round(Math.random()*(max-min)+min);
		return String.valueOf(temp);
	}
}
