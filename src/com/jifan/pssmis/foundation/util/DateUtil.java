package com.jifan.pssmis.foundation.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * 日期处理类
 * 
 * @author jifan
 */
public class DateUtil {
	public static final long minute = 1000 * 60;

	public static final String FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_ALL_23 = "yyyy-MM-dd 23:59:59";

	public static final String FORMAT_DAY = "yyyy-MM-dd";

	public static Date getPreviousThreeMonth(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int offMonthSet;
		int offDay = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		int offYear = calendar.get(GregorianCalendar.YEAR);

		int offMonth = calendar.get(GregorianCalendar.MONTH);
		if (offMonth < 2) {
			offMonthSet = 11 - 3;
			offYear = offYear - 1;
		} else {
			offMonthSet = offMonth - 3;
		}
		if (offMonthSet == 1) {
			if (offYear % 4 == 0) {
				if (offDay > 29) {
					offDay = 29;
				}
			} else {
				if (offDay > 28) {
					offDay = 28;
				}
			}
		}
		calendar.set(offYear, offMonthSet, offDay);
		return calendar.getTime();
	}

	/**
	 * 获得截止时间和起始时间之间的日期差天数。可返回负数。
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            截止时间
	 * @return 返回两个日期相差的天数
	 */
	public static Long getDays(Date startDate, Date endDate) {
		Long days = new Long(0);
		if (startDate != null && endDate != null)
			days = ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24);
		return days;
	}

	/**
	 * 获得截止时间和起始时间之间的日期差天数。可返回负数。
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            截止时间
	 * @return 返回两个日期相差的天数
	 */
	public static Long getDayRange(Date startDate, Date endDate) {
		Long days = new Long(0);
		if (startDate != null && endDate != null)
			days = (endDate.getTime() / 1000 / 60 / 60 / 24 - startDate.getTime() / 1000 / 60 / 60 / 24);
		return days;
	}

	/**
	 * 获取给定日期的月份上个月的第一天
	 * 
	 * @param currDate
	 * @return
	 */
	public static Date getFirstDayOfPreMonth(Date currDate) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(currDate); // currDate 为你要获取的那个月的时间
		// 清零时分秒
		ca = getCalendarByObject(ca, true);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, -1);
		Date firstDate = ca.getTime();
		return firstDate;
	}
	
	public static Date reportGetDate(Date d, String type, int number) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if (type.equals("YEAR"))
			calendar.add(Calendar.YEAR, number);
		if (type.equals("MONTH"))
			calendar.add(Calendar.MONTH, number);
		if (type.equals("DAY"))
			calendar.add(Calendar.DAY_OF_MONTH, number);
		if (type.equals("HOUR"))
			calendar.add(Calendar.HOUR_OF_DAY, number);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 求当前时间
	 * 
	 * @param time
	 * @return
	 */
	public static String yiQtime(Date date1, int time) {
		Date date = new Date(System.currentTimeMillis());

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA);
		String dt = df.format(date);
		System.out.println("***" + dt);

		date = new Date(System.currentTimeMillis() - time * 60 * 60 * 1000); // 半小时以后的时间
		dt = df.format(date);
		System.out.println(dt);

		return dt;
	}

	/**
	 * 已知的日期是否在某个日期期间内
	 * 
	 * @param d
	 * @param type
	 * @param number
	 * @return
	 */
	public static boolean isDateBetween(Date minDate, Date maxDate, Date minDate2, Date maxDate2) {
		return !(maxDate2.compareTo(minDate) < 0 || minDate2.compareTo(maxDate) > 0);
	}

	/**
	 * 获取当前时间的 格式为yyyy-MM-dd HH:mm:ss 字符串。
	 * 
	 * @return String 当前时间yyyy-MM-dd HH:mm:ss
	 */
	public static String getStrNowDate() {
		return format(new Date(), null);
	}

	/**
	 * 获取当前时间的 格式为yyyy-MM-dd HH:mm:ss 字符串。
	 * 
	 * @return String 当前时间yyyy-MM-dd HH:mm:ss
	 */
	public static String getStrNowDateChina() {
		return format(new Date(), "yyyy年MM月dd日");
	}

	/**
	 * 之所以不建议继续使用该方法，是大家可以调用该类中的 public static String format(Date date, String
	 * pattern) 这个方法。 获取某一日期 格式为yyyy-MM-dd HH:mm:ss的字符串
	 * 
	 * @param date
	 *            需要转化字符串的日期参数
	 * @return String 日期字符串格式：yyyy-MM-dd HH:mm:ss
	 */
	@Deprecated
	public static String getTotalDate(Date date) {
		return format(date, null);
	}

	/**
	 * 之所以不建议继续使用该方法，是大家可以调用该类中的 public static String format(Date date, String
	 * pattern) 这个方法。 获取某一日期的 格式为yyyy-MM-dd 的字符串形式。create by liuguohau 20080604
	 * 
	 * @param date
	 *            需要转化字符串的日期参数
	 * @return String 日期字符串格式：yyyy-MM-dd -/
	 * @Deprecated public static String getTotalDate2(Date date) { return
	 *             format(date,"yyyy-MM-dd"); }
	 * 
	 *             /** 获取当前时间的 格式为yyyy-MM-dd字符串
	 * 
	 * @return String 当前时间yyyy-MM-dd
	 */
	public static String getStrNowDateShort() {
		return format(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间 格式为yyyyMMdd 的字符串
	 * 
	 * @return String 当前时间yyyyMMdd
	 */
	public static String getStrDateShort() {
		return format(new Date(), "yyyyMMdd");
	}

	/**
	 * 获取当前时间 格式为yyMMdd 的字符串
	 * 
	 * @return String 当前时间yyMMdd
	 */
	public static String getStrDateShort2() {
		return format(new Date(), "yyMMdd");
	}

	/**
	 * 获取当前时间 格式为yyyyMMddhhmmss 的字符串形式
	 * 
	 * @return
	 */
	public static String getStrLongDate() {
		return format(new Date(), "yyyyMMddhhmmss");
	}

	/**
	 * 获取当前时间 格式为 hhmmssZ 的字符串形式
	 * 
	 * @return
	 */
	public static String getStrTime() {
		return format(new Date(), "hhmmssZ");
	}

	/**
	 * 字符串转化为日期型
	 * 
	 * @param str
	 *            String 格式yyyy-MM-dd HH:mm:ss或者yyyy-MM-dd
	 * @return Date
	 */
	public static Date strToDate(String str) {
		if (str.trim().length() < 12) {
			return strToDateShort(str);
		} else {
			return strToDateLong(str);
		}
	}

	/**
	 * 将型如yyyy-MM-dd的字符串转化为日期
	 * 
	 * @param strDate
	 *            型如‘yyyy-MM-dd’的字符串
	 * @return Date 返回日期型对象
	 */
	public static Date strToDateShort(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将型如yyyy-MM-dd HH:mm:ss的字符串转化为日期
	 * 
	 * @param strDate
	 *            型如‘yyyy-MM-dd HH:mm:ss’的字符串
	 * @return 返回日期型对象
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将日期转化为特定的字符串形式，当格式不对的时候，会抛出异常
	 * 
	 * @param date
	 *            可为空，需要转化字符串的日期
	 * @param pattern
	 *            可为空，转化字符串的形式：如：yyyy-MM-dd,默认为：yyyy-MM-dd HH:mm:ss
	 * @return 返回日期转化后的字符串
	 */
	public static String format(Object date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formatter;
		try {
			if (CommonUtil.isEmpty(pattern)) {
				pattern = "yyyy-MM-dd HH:mm:ss";
			}
			formatter = new SimpleDateFormat(pattern);
		} catch (RuntimeException e) {
			throw new RuntimeException("字符串格式错误！");
		}
		try {
			String dateString = formatter.format(date);
			return dateString;
		} catch (RuntimeException e) {
			throw new RuntimeException("要转化的对象必须是日期型对象！");
		}
	}

	/**
	 * 把日期 转化为型如yyyy-MM-dd 格式的字符串
	 * 
	 * @param date
	 *            需要转化的日期型对象
	 * @return String 返回型如”yyyy-MM-dd“的字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}
	
	/**
	 * 把日期 转化为型如yyyy-MM-dd 格式的字符串
	 * 
	 * @param date
	 *            需要转化的日期型对象
	 * @return String 返回型如”yyyy-MM-dd“的字符串
	 */
	public static String formatShort(Date date) {
		return format(date, "MMdd");
	}

	/**
	 * 把日期 转化为型如yMM-dd 格式的字符串
	 * 
	 * @param date
	 *            需要转化的日期型对象
	 * @return String 返回型如”yyyy-MM-dd“的字符串
	 */
	public static String formatMd(Date date) {
		return format(date, "MM-dd");
	}

	/**
	 * 获取当前系统时间的日期,格式为yyyy-MM-dd
	 * 
	 * @return Date 返回java.sql.Date类型
	 */
	public static java.sql.Date getNow() {
		java.sql.Date date = java.sql.Date.valueOf(getStrNowDateShort());
		return date;
	}

	/**
	 * 获取系统时间的格式为yyyy-MM-dd HH:mm:ss 的字符串
	 * 
	 * @return
	 */
	public static String getDateTime() {
		String date = getStrNowDate();
		return date;
	}

	/**
	 * 获取时间date的长整型
	 * 
	 * @param date
	 *            Date型对象。
	 * @return long
	 */
	public static long getLongTime(Date date) {
		return date.getTime();
	}

	/**
	 * 获取当前系统时间的长整型
	 * 
	 * @return long
	 */
	public static long getLongTime() {
		return getLongTime(new Date());
	}

	/**
	 * parse the java.util.Date type to java.sql.Date *
	 * 
	 * @param source
	 *            java.util.Date
	 * @return java.sql.Date
	 */
	public static java.sql.Date parseDateToSQLDate(Date source) {
		if (source == null) {
			return null;
		}
		return new java.sql.Date(source.getTime());
	}

	/**
	 * 将字符串转化为java.sql.Date型的日期,目前支持的字符串类型为： 格式yyyy-MM-dd HH:mm:ss或者yyyy-MM-dd
	 * 
	 * @param source
	 *            需要转化日期的字符串
	 * @return java.sql.Date
	 */
	public static java.sql.Date parseStrToSQLDate(String source) {
		if (CommonUtil.isEmpty(source)) {
			return null;
		}
		return parseDateToSQLDate(strToDate(source));
	}

	/**
	 * 返回给定日期的 会计月份，型如 YYYYMM 的字符串
	 * 
	 * @param date
	 * @return 返回日期的 型如 YYYYMM 的字符串
	 */
	public static String getFiscalmonth(Date date) {
		return format(date, "yyyyMM");
	}

	/**
	 * 返回当前日期的会计月份，型如 YYYYMM 的字符串
	 * 
	 * @return 返回日期的 型如 YYYYMM 的字符串
	 */
	public static String getFiscalmonth() {
		return getFiscalmonth(new Date());
	}

	/**
	 * 获取指定日期的年份的字符串形式，型如：yyyy
	 * 
	 * @return
	 */
	public static String getCurYear(Date date) {
		return format(date, "yyyy");
	}

	/**
	 *获取当年所属年份的字符串形式，型如：yyyy
	 * 
	 * @return
	 */
	public static String getCurYear() {
		return getCurYear(new Date());
	}

	/**
	 * 获取指定日期的月份的字符串形式，字符串形式为 formatStr给定
	 * 
	 * @param date
	 *            要转化的日期对象
	 * @param formatStr
	 *            转化的月份的字符串格式，默认为："MM"
	 * @return
	 */
	public static String getCurMonth(Date date, String formatStr) {
		if (CommonUtil.isEmpty(formatStr)) {
			formatStr = "MM";
		}
		return format(date, formatStr);
	}

	/**
	 * 获取当前日期的月份的字符串形式，字符串形式为 "M"
	 * 
	 * @return
	 */
	public static String getCurMonth2() {
		return getCurMonth(new Date(), "M");
	}

	/**
	 * 获取当前日期的月份的字符串形式，字符串形式为 "MM"
	 * 
	 * @return
	 */
	public static String getCurMonth() {
		return getCurMonth(new Date(), "MM");
	}

	/**
	 * 将日期型对象转化为型如 2012年02月24日的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormatedDate(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			StringBuffer bf = new StringBuffer();
			bf.append(String.format("%04d", cal.get(Calendar.YEAR)));
			bf.append("年");
			bf.append(String.format("%02d", cal.get(Calendar.MONTH) + 1));
			bf.append("月");
			bf.append(String.format("%02d", cal.get(Calendar.DATE)));
			bf.append("日");
			return bf.toString();
		}
		return "";
	}

	/**
	 * 获得某一日期，所处的当年的周数
	 * 
	 * @param date
	 * @return int
	 */
	public static int getCurWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获得某一日期，所处的当月的周数
	 * 
	 * @param date
	 * @return int
	 */
	public static int getCurWeekOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week < 0) {
			week = 7;
		}
		if (week == 0) {
			week = 1;
		}
		return week;
	}

	/**
	 * 获得某一日期，所处的当年的月数，这里查询出来的数比实际少1。比如：现在是 2012-02-24，则查询出来的月数为1
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得当前日期，所处的当年的周数
	 * 
	 * @return
	 */
	public static int getCurWeekOfYear() {
		return getCurWeekOfYear(new Date());
	}

	/**
	 * 将当前日期转化为特殊形式的字符传。
	 * 
	 * @param type
	 *            转化字符串的类型。默认为：1，即转化后的字符串形式默认为："yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getCurDateFormat(Integer type) {
		if (type == null) {
			type = 1;
		}
		Calendar cal = Calendar.getInstance();
		String dYear = String.format("%04d", cal.get(Calendar.YEAR));
		String dMonth = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		String dDate = String.format("%02d", cal.get(Calendar.DATE));
		String dHour = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
		String dMinute = String.format("%02d", cal.get(Calendar.MINUTE));
		String dSecond = String.format("%02d", cal.get(Calendar.SECOND));
		int dWeekDay = cal.get(Calendar.DAY_OF_WEEK);
		String dWeekDayZh = "";
		switch (dWeekDay) {
		case 1:
			dWeekDayZh = "日";
			break;
		case 2:
			dWeekDayZh = "一";
			break;
		case 3:
			dWeekDayZh = "二";
			break;
		case 4:
			dWeekDayZh = "三";
			break;
		case 5:
			dWeekDayZh = "四";
			break;
		case 6:
			dWeekDayZh = "五";
			break;
		case 7:
			dWeekDayZh = "六";
			break;
		}
		StringBuffer sbfDate = new StringBuffer();
		switch (type) {
		case 1:
			sbfDate.append(dYear + "-" + dMonth + "-" + dDate + "  " + dHour + ":" + dMinute + ":" + dSecond);
			break;
		case 2:
			sbfDate.append(dYear + "年" + dMonth + "月" + dDate + "日*星期" + dWeekDayZh);
			break;
		case 3:
			sbfDate.append(dYear + "." + dMonth + "." + dDate);
			break;
		case 4:
			sbfDate.append(dYear + "-" + dMonth + "-" + dDate);
			break;
		default:
			sbfDate.append(dYear + "-" + dMonth + "-" + dDate + "  " + dHour + ":" + dMinute + ":" + dSecond);
		}
		return sbfDate.toString();
	}

	public static int getRegYears(String regTime) {
		return 1;
	}

	/**
	 * 将long型的数据转化为型如 "yyyy-MM-dd hh:mm:ss"的字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String parseToDateStr(long time) {
		return parseToDateStr(time, "yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * 将long型的数据转化为特定格式的字符串。默认格式为为："yyyy-MM-dd hh:mm:ss"
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String parseToDateStr(long time, String format) {
		if (time == 0) {
			return null;
		}
		return format(new Date(time), format);
	}

	/**
	 * 找出给定日期的所在月的最后一天
	 * 
	 * @param c
	 * @return
	 */

	public static Date getLastDay(Date c) {
		if (c == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(c);

		// 清除时分秒
		calendar = getCalendarByObject(calendar, true);

		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return calendar.getTime();
	}

	/**
	 * 获取给定日期的上个月的最后一天的日期对象
	 * 
	 * @param c
	 *            给定的日期对象
	 * @return
	 */
	public static Date getLastDayOfPreMonth(Date c) {
		if (c == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(c);
		// 清除时分秒
		calendar = getCalendarByObject(calendar, true);

		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 将一个对象转化为Calendar对象，
	 * 
	 * @param object
	 *            需要转化的对象，目前只支持 Calendar、java.util.Date这两种对象。其他对象返回null
	 * @param clear
	 *            可为null，是否将时分秒清零。true为清零，否则不清零，默认为不清零
	 * @return
	 */
	public static Calendar getCalendarByObject(Object object, Boolean clear) {
		Calendar calendar = null;
		if (object instanceof Date) {
			calendar = Calendar.getInstance();
			calendar.setTime((Date) object);
		} else if (object instanceof Calendar) {
			calendar = (Calendar) object;
		}
		if (calendar != null) {
			if (Boolean.TRUE.equals(clear)) {
				// 清除时分秒
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.clear(Calendar.MINUTE);
				calendar.clear(Calendar.SECOND);
			}
		}

		return calendar;
	}

	/**
	 * 获取当前日期的偏移 holdDays 天数后的型如 yyyy-MM-dd 的字符串形式
	 * 
	 * @param holdDays
	 *            往前完后偏移的天数
	 * @param beforeCurrentDate
	 *            标志往前还是往后。若为true，则是当前日期的前面。 否则为：当前日期的后面
	 * @return
	 */
	public static String getDate(int holdDays, boolean beforeCurrentDate) {
		String formatStr = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (beforeCurrentDate) {
			holdDays = -holdDays;
		}
		calendar.add(Calendar.DATE, holdDays);
		return formatter.format(calendar.getTime());
	}

	/**
	 * 获取当前时间对应的季度的字符串形式：如：YYYY01；01为季度
	 * 
	 * @return
	 */
	public static String getCurQuarter() {
		int month = Integer.parseInt(getCurMonth());
		int quarter = (month + 2) / 3;
		return getCurYear() + "0" + String.valueOf(quarter);
	}

	/**
	 * 获取当前旬的序数字符串：01，02，03
	 * 
	 * @return
	 */
	public static String getCurXun() {
		int date = Integer.parseInt(getCurMonth(new Date(), "dd"));
		if (date <= 10) {
			return "01";
		} else if (date <= 20) {
			return "02";
		} else {
			return "03";
		}
	}

	/**
	 * 将日期的字符串形式转化为日期对象，目前支持转化的字符串类型为： 1."dd/MM/yyyy" 2. "yyyy-MM-dd"
	 * 3."yyyy年MM月dd日" 4."EEE MMM dd HH:mm:ss 'CST' yyyy" 若字符串形式有错，则会返回null
	 * 
	 * @param strDate
	 *            日期的字符串形式
	 * @return Date 返回转化后的日期对象，没有则返回null
	 */
	public static Date ToDateShort(String strDate) {
		String format;
		int indexOne = strDate.indexOf('/');
		int indexTwo = strDate.indexOf('-');
		int indexThree = strDate.indexOf('年');
		SimpleDateFormat formatter;
		if (indexOne > 0) {
			format = "dd/MM/yyyy";
			formatter = new SimpleDateFormat(format);
		} else if (indexTwo > 0) {
			format = "yyyy-MM-dd HH:mm";
			formatter = new SimpleDateFormat(format);
		} else if (indexThree > 0) {
			format = "yyyy年MM月dd日";
			formatter = new SimpleDateFormat(format);
		} else {
			format = "EEE MMM dd HH:mm:ss 'CST' yyyy";
			formatter = new SimpleDateFormat(format, Locale.US);
		}
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 获取给定日期的月份第一天
	 * 
	 * @param currDate
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date currDate) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(currDate); // currDate 为你要获取的那个月的时间
		// 清零时分秒
		ca = getCalendarByObject(ca, true);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		return firstDate;
	}

	/**
	 * 获取给定日期的所在月份的最后一天
	 * 
	 * @param currDate
	 * @return
	 */
	public static Date getLastDayOfMonth(Date currDate) {
		return getLastDay(currDate);
	}

	/**
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSecond(Date date, int seconds) {
		// if (date == null)
		// return null;
		// GregorianCalendar calendar = new GregorianCalendar();
		// calendar.setTime(date);
		// calendar.add(GregorianCalendar.SECOND, seconds);
		// return calendar.getTime();
		return addDateByUnit(date, Calendar.SECOND, seconds, false);
	}

	/**
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date getDate(Date date, int hour, int seconds) {
		if (date == null)
			return null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(GregorianCalendar.HOUR_OF_DAY, hour);
		calendar.set(GregorianCalendar.MINUTE, seconds);
		calendar.set(GregorianCalendar.MILLISECOND, 0);
		return calendar.getTime();

	}

	/**
	 * 判断指定一个对象是否为日期对象。目前只简称判断两种类型的对象：java.util.Calendar， java.util.Date。
	 * 
	 * @param date
	 *            要判断的日期对象
	 * @return
	 */
	public static boolean flagObjectIsDateObject(Object date) {
		if (date instanceof Date)
			return true;
		if (date instanceof Calendar)
			return true;
		return false;
	}

	/**
	 * 根据给定单位和数量，对日期对象的特定位置增加特定的数量，比如;小时，月份等
	 * 
	 * @param dateObject
	 *            要增加数量的日期对象，目前只简称判断两种类型的对象：java.util.Calendar， java.util.Date。
	 * @param calendarUnit
	 *            这个变量是引用Calendar的静态变量
	 * @param addint
	 *            要增加的数量，数量为负数的时候是减少对应数量的单位时间
	 * @param flagClearTime
	 *            可为null，是否将时分秒清零。true为清零，否则不清零，默认为不清零
	 * @return 返回一个java.util.Date对象
	 */
	public static Date addDateByUnit(Object dateObject, int calendarUnit, int addint, boolean flagClearTime) {
		if (!flagObjectIsDateObject(dateObject))
			throw new RuntimeException("给定的对象不是日期对象");
		Calendar calendar = getCalendarByObject(dateObject, flagClearTime);
		calendar.add(calendarUnit, addint);
		return calendar.getTime();
	}

	/**
	 * 由给定日期计算是该年的第几天
	 */
	public static int getDayOfYear(Date date) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.DAY_OF_YEAR);
		} else {
			return -1;
		}
	}

	/**
	 * 由给定日期计算是该月的第几天
	 */
	public static int getDayOfMonth(Date date) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.DAY_OF_MONTH);
		} else {
			return -1;
		}
	}

	/**
	 *给定日期去掉年份
	 */
	public static String removeYear(Date date) {
		if (date != null && !date.equals("")) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH);
		} else {
			return "";
		}
	}

	/*
	 * 获取时间段中所有的周六
	 */
	public static List<Object[]> getSat(Date begin, Date end) {
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		List<Object[]> list = new ArrayList<Object[]>();
		c.setTime(begin);
		c1.setTime(end);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			Object[] obj = new Object[2];
			obj[0] = c.get(Calendar.WEEK_OF_YEAR);
			obj[1] = c.getTime();
			list.add(obj);
		}
		while (c1.after(c)) {
			c.add(Calendar.DATE, 1);
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				Object[] obj = new Object[2];
				obj[0] = c.get(Calendar.WEEK_OF_YEAR);
				obj[1] = c.getTime();
				list.add(obj);
			}
		}
		return list;
	}

	/*
	 * 获取时间段中所有的周日
	 */
	public static List<Object[]> getSun(Date begin, Date end) {
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		List<Object[]> list = new ArrayList<Object[]>();
		c.setTime(begin);
		c1.setTime(end);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			Object[] obj = new Object[2];
			obj[0] = c.get(Calendar.WEEK_OF_YEAR);
			obj[1] = c.getTime();
			list.add(obj);
		}
		while (c1.after(c)) {
			c.add(Calendar.DATE, 1);
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				Object[] obj = new Object[2];
				obj[0] = c.get(Calendar.WEEK_OF_YEAR);
				obj[1] = c.getTime();
				list.add(obj);
			}
		}
		return list;
	}

	/*
	 * 获取所有
	 */
	public static List<Object[]> getAll(Date begin, Date end) {
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		List<Object[]> list = new ArrayList<Object[]>();
		c.setTime(begin);
		c1.setTime(end);
		Object[] obj = new Object[2];
		obj[0] = c.get(Calendar.DAY_OF_YEAR);
		obj[1] = c.getTime();
		list.add(obj);
		while (c1.after(c)) {
			c.add(Calendar.DATE, 1);
			Object[] obj1 = new Object[2];
			obj1[0] = c.get(Calendar.DAY_OF_YEAR);
			obj1[1] = c.getTime();
			list.add(obj1);
		}
		return list;
	}

	/*
	 * 获取时间段中所有的周1到周5
	 */
	public static List<Object[]> getMonToFri(Date begin, Date end) {
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		List<Object[]> list = new ArrayList<Object[]>();
		c.setTime(begin);
		c1.setTime(end);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY || c
			.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY || c
			.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			Object[] obj = new Object[2];
			obj[0] = c.get(Calendar.DAY_OF_YEAR);
			obj[1] = c.getTime();
			list.add(obj);
		}
		while (c1.after(c)) {
			c.add(Calendar.DATE, 1);
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY || c
				.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY || c
				.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				Object[] obj = new Object[2];
				obj[0] = c.get(Calendar.DAY_OF_YEAR);
				obj[1] = c.getTime();
				list.add(obj);
			}
		}
		return list;
	}

	/**
	 * 求两个日期之间的间隔
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getDisday(Date beginDate, Date endDate) {
		int day = 1000 * 3600 * 24;
		Long invalidDate = (endDate.getTime() - beginDate.getTime()) / day;
		return invalidDate.intValue();
	}

	/**
	 * 获取date下一个月的日期
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getNextDay(java.util.Date date) {

		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, 1);
		return gc.getTime();
	}

	/**
	 * @author zwl
	 * @Version 1.0
	 * @date Mar 8, 2011-12
	 * @Description 求指定日期的+天数
	 * @return List<String>
	 */
	public static String getAfterDate(Date date, int days) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
		return df.format(calendar.getTime());
	}

	/**
	 * 求指定日期的下一个月
	 */
	public static Date lastDate(Date lastDay) {
		return strToDateShort(getAfterDate(getLastDayOfMonth(lastDay), 1));
	}

	/**
	 * 求指定日期 的后n个月的日期
	 */
	public static Date getMonthAdd(Date theDate, int addMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		calendar.add(Calendar.MONTH, addMonth);
		return calendar.getTime();
	}

	/**
	 * @author zwl
	 * @Version 1.0
	 * @date Mar 8, 2011-12
	 * @Description 求指定日期的减去天数
	 * @return List<String>
	 */
	public static String getBeforeDate(Date date, int days) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
		return df.format(calendar.getTime());
	}

	/**
	 * @author zwl
	 * @Version 1.0
	 * @date Mar 8, 2011-12
	 * @Description 求指定日期的减去天数
	 * @return List<String>
	 */
	public static String getBeforeDates(Date date, int days) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
		return df.format(calendar.getTime());
	}

	/**
	 * 把日期 转化为型如yyyy-MM 格式的字符串
	 * 
	 * @param date
	 *            需要转化的日期型对象
	 * @return String 返回型如”yyyy-MM-dd“的字符串
	 */
	public static String formatYM(Date date) {
		return format(date, "yyyy-MM");
	}

	/**
	 * 
	 * @description： 计算2个日期间隔天数
	 * @author fangyuan
	 * @date 2014-6-17 上午10:39:21
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return (int) between_days;
	}

	/**
	 * 
	 * @description： 判断开始时间 是否在结束时间的一年外
	 * @author fangyuan
	 * @date 2015-2-3 下午04:04:14
	 */
	public static boolean daysBetweenTwoDate(Date beginDate, Date endDate) {
		long s1 = beginDate.getTime();
		long s2 = endDate == null ? 0 : endDate.getTime();
		if ((s2 + 1000 * 60 * 60 * 24 * 365) < s1) {
			return true;
		}
		return false;
	}

	/**
	 * 获取日期之前或之后n天
	 * 
	 * @param theDate
	 *            原日期
	 * @param days
	 *            前后天数，前为负，前为正
	 * @return
	 */
	public static Date getDataAdd(Date theDate, Integer days) {
		if(theDate==null)
			theDate = new Date();
		if(days==null)
			days=0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * @Title: getStartTime
	 * @Description: 得到某个时间的那一天起始时间
	 * @param: @param date
	 * @param: @return
	 * @return: Date
	 * @throws
	 * @author XUJUN
	 * @email 15059697@qq.com
	 * @Date 2016年1月18日 下午4:42:35
	 */
	public static Date getStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * @Title: getEndTime
	 * @Description: 得到某个时间的那一天结束时间
	 * @param: @param date
	 * @param: @return
	 * @return: Date
	 * @throws
	 * @author XUJUN
	 * @email 15059697@qq.com
	 * @Date 2016年1月18日 下午4:45:40
	 */
	public static Date getEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	/**
	 * 求两个日期之间的间隔
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Map<String, List<String>> getDaysMap(Date beginDate, Date endDate) {
		Map<String, List<String>> map = new TreeMap<String, List<String>>();
		if (beginDate != null && endDate != null) {
			int beginMonth = getMonthOfYear(beginDate);// 开始日期的月份
			int endMonth = getMonthOfYear(endDate);// 结束日期的月份
			int beginMonthDay = getDayOfMonth(beginDate);// 开始日期的月天
			int endMonthDay = getDayOfMonth(endDate);// 结束日期的月天
			List<String> daysList = new ArrayList<String>();// 日期列表
			if (beginMonth == endMonth) {
				daysList = new ArrayList<String>();
				// long days =getDays(beginMonthFirstDate, beginMonthLastDate);
				for (int i = beginMonthDay; i <= endMonthDay; i++) {
					daysList.add(String.valueOf(i));
				}
				map.put(String.valueOf(beginMonth), daysList);
			} else {
				daysList = new ArrayList<String>();
				Date beginMonthLastDate = getLastDayOfMonth(beginDate);// 开始日期的月最后一天
				// 开始月份
				int beginMonthLastDay = getDayOfMonth(beginMonthLastDate);
				for (int i = beginMonthDay; i <= beginMonthLastDay; i++) {
					daysList.add(String.valueOf(i));
				}
				map.put(String.valueOf(beginMonth), daysList);

				// 中间月份
				int midMonths = 0;// 开始日期和结束日期相差的月份数，不含开始结束日期的月份
				if (endMonth > beginMonth)
					midMonths = endMonth - beginMonth;
				else
					midMonths = 12 - beginMonth + endMonth - 1;
				Date midDate;
				for (int i = 1; i <= midMonths; i++) {
					daysList = new ArrayList<String>();
					// 获取中间月份的月份值
					midDate = getMonthAdd(beginDate, i);
					int midMonthDays = getDayOfMonth(getLastDayOfMonth(midDate));
					int midMonth = getMonthOfYear(midDate);
					for (int j = 1; j <= midMonthDays; j++) {
						daysList.add(String.valueOf(j));
					}
					map.put(String.valueOf(midMonth), daysList);
				}

				// 结束月份
				daysList = new ArrayList<String>();
				for (int i = 1; i <= endMonthDay; i++) {
					daysList.add(String.valueOf(i));
				}
				map.put(String.valueOf(endMonth), daysList);

			}
		}
		return map;

	}

	public static void main(String[] tr) {
		Date date1 = strToDate("2016-01-15");
		Date date2 = strToDate("2016-04-15");
		Map<String, List<String>> map = DateUtil.getDaysMap(date1, date2);
		for (String key : map.keySet()) {
			System.out.println(key + "月份");
			List<String> list = map.get(key);
			for (String str : list) {
				System.out.println(key + "月份" + str);
			}
		}
	}
}