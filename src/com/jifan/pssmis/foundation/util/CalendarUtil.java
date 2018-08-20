package com.jifan.pssmis.foundation.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期工具
 * 
 * @author jifan
 */
public class CalendarUtil {
	private final static String TERMUNIT = "TermUnit";

	private final static String TERMUNIT_S1 = "S1";

	private final static String TERMUNIT_S2 = "S2";

	private final static String TERMUNIT_S3 = "S3";

	/**
	 * 获取dateline
	 * 
	 * @return
	 */
	public static String getCurrentDateline() {
		String dateline = System.currentTimeMillis() + "";
		return dateline.substring(0, dateline.length() - 3);
	}

	/**
	 * 计算两个日期相差的天数，只考虑年月日，不考虑时分秒(如果加上时分秒，可能导致天数计算错误)
	 * 
	 * @param before
	 * @param after
	 * @return long 相差的天数
	 */
	public static int getDateDiff(Date before, Date after) {
		if (before == null || after == null)
			return 0;
		Calendar calendar1 = new GregorianCalendar();
		Calendar calendar2 = new GregorianCalendar();
		calendar1.setTime(before);
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		calendar2.setTime(after);
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		calendar2.set(Calendar.MILLISECOND, 0);
		int diff = (int) ((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 60 * 60 * 1000));
		return diff;
	}

	/**
	 * 计算两个日期相差的天数，不考虑日期前后，返回结果>=0
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int getAbsDateDiff(Date before, Date after) {
		int diff = getDateDiff(before, after);
		return Math.abs(diff);
	}

	/**
	 * 比较第一个日期是否早于第二个日期 利用了getDateDiff方法，如果两者相差天数>0,则为true;
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static boolean getDateIsBefore(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return false;
		int diff = getDateDiff(d1, d2);
		if (diff > 0)
			return true;
		return false;
	}

	/**
	 * 比较第一个日期是否晚于第二个日期 利用了getDateDiff方法，如果两者相差天数<0,则为true;
	 * 
	 * @param before
	 * @param after
	 * @return boolean
	 */
	public static boolean getDateIsAfter(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return false;
		int diff = getDateDiff(d1, d2);
		if (diff < 0)
			return true;
		return false;
	}

	/**
	 * 比较第一个日期是否晚于第二个日期 利用了getDateDiff方法，如果两者相差天数<0,则为true;
	 * 
	 * @param before
	 * @param after
	 * @return boolean
	 */
	public static boolean getDateIsAfterMill(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return false;
		if (d1.getTime() >= d2.getTime())
			return true;
		return false;
	}

	/**
	 * 比较第一个日期是否晚于第二个日期 并且在31天内;
	 * 
	 * @param before
	 * @param after
	 * @return boolean
	 */
	public static boolean getDateIsAfter31(Date d1, Date d2, int dates) {
		if (d1 == null || d2 == null)
			return false;
		if (d1.getTime() < d2.getTime()) {
			return false;
		}
		if (d1.getTime() >= d2.getTime()) {
			long time = d1.getTime() - d2.getTime();
			long t = time / (24 * 60 * 60 * 1000);
			if (t > dates)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Timestamp转成Calendar
	 * 
	 * @param timestamp
	 * @return Calendar
	 * 
	 */
	public static Calendar timestampToCalendar(Timestamp timestamp) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(timestamp.getTime());
		calendar.getTime();// 立即刷新
		return calendar; // 不能使用getInstance,否则变当时状态
	}

	/**
	 * Calendar转成Timestamp
	 * 
	 * @param calendar
	 * @return Timestamp
	 * 
	 */
	public static Timestamp calendarToTimestamp(Calendar calendar) {
		return new Timestamp(calendar.getTimeInMillis());// 不能使用getInstance,否则变成当时状态

	}

	/**
	 * 根据开始日期和结束日期计算两个日期的工作日天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param holidays
	 *            节假日集合 Date of List(yyyy-MM-dd) 也可为空（为空的情况忽略法定节假日）
	 * @param weekEndWords
	 *            周末为工作日的日期集合
	 * @return int 返回工作天数
	 */
	public static int countWorkingDays(Date startDate, Date endDate, List holidays, List weekEndWorkDays) {
		Date date;
		if (getDateIsBefore(startDate, endDate)) {
			date = startDate;
		} else {
			date = endDate;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int count = 0;
		while (!date.after(endDate)) {
			if (isWorkDay(date, holidays, weekEndWorkDays)) {// 判断是否为工作日，如果为工作日则加1
				count++;
			}
			cal.add(Calendar.DATE, 1);// 日期向后加1天
			date = cal.getTime();
		}

		return count;
	}

	/**
	 * 根据当前日期和需要向后推的工作天数计算工作日
	 * 
	 * @param curDate
	 *            当前日期
	 * @param num
	 *            需要向后推的工作日天数
	 * @param holidays
	 *            节假日集合 Date of List(yyyy-MM-dd) 也可为空（为空的情况忽略法定节假日）
	 * @param weekEndWorkDays
	 *            周末工作日集合
	 * @return Date 返回下个工作日
	 */
	public static Date getNextWorkDay(Date curDate, int num, List holidays, List weekEndWorkDays) {
		return getWorkDay(curDate, num, holidays, weekEndWorkDays, true);
	}

	/**
	 * 根据当前日期和需要向后推的工作天数计算工作日
	 * 
	 * @param curDate
	 *            当前日期
	 * @param num
	 *            需要向前推的工作日天数
	 * @param holidays
	 *            节假日集合 Date of List(yyyy-MM-dd) 也可为空（为空的情况忽略法定节假日）
	 * @param weekEndWorkDays
	 *            周末工作日集合
	 * @return Date 返回上个个工作日
	 */
	public static Date getForwardWorkDay(Date curDate, int num, List holidays, List weekEndWorkDays) {
		return getWorkDay(curDate, num, holidays, weekEndWorkDays, false);
	}

	/**
	 * 根据当前日期和需要向后推的工作天数计算工作日
	 * 
	 * @param curDate
	 *            当前日期
	 * @param num
	 *            需要推的工作日天数
	 * @param holidays
	 *            节假日集合 Date of List(yyyy-MM-dd) 也可为空（为空的情况忽略法定节假日）
	 * @param weekEndWorkDays
	 *            周末工作日集合
	 * @param added
	 *            是加或减 true向前推 false向后推
	 * @return Date 返回工作日日期
	 */
	private static Date getWorkDay(Date curDate, int num, List holidays, List weekEndWorkDays, boolean added) {
		Date endDate = curDate;
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < num; i++) {
			cal.setTime(endDate);
			if (added) {
				cal.add(Calendar.DATE, 1);// 日期向后加1天
			} else {
				cal.add(Calendar.DATE, -1);// 日期向前加1天
			}
			endDate = cal.getTime();
			if (!isWorkDay(endDate, holidays, weekEndWorkDays)) {
				i--;
			}
		}
		return endDate;
	}

	/**
	 * 根据日期判断是否为工作日
	 * 
	 * @param date
	 *            需要判断的日期
	 * @param holidays
	 *            法定假日集合
	 * @param weekEndWorkDays
	 *            周末工作日集合
	 * @return 是工作日返回true,不是工作日返回false;
	 */
	public static boolean isWorkDay(Date date, List holidays, List weekEndWorkDays) {
		int day_of_week = CalendarUtil.getDayOfWeek(date);
		if (day_of_week == 1 && !isHoliday(date, weekEndWorkDays)) {// 星期六
			return false;
		} else if (day_of_week == 7 && !isHoliday(date, weekEndWorkDays)) {// 星期天
			return false;
		}
		if (holidays != null && holidays.size() > 0) {
			if (isHoliday(date, holidays)) {// 判断是否为法定假日
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否为法定假日
	 * 
	 * @param date
	 *            需要判断的日期
	 * @param holidays
	 *            假日集合
	 * @return true为法定假日，false为工作日
	 */
	public static boolean isHoliday(Date date, List holidays) {
		if (holidays != null && holidays.size() > 0) {
			Date holiday;
			for (int j = 0; j < holidays.size(); j++) {
				holiday = (Date) holidays.get(j);
				if (getDateDiff(holiday, date) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 为日期排序
	 * 
	 * @List list 日期集合 List of Date
	 */
	public static List sortDays(List list) {
		Collections.sort(list, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				if (((Date) arg0).getTime() > ((Date) arg1).getTime())
					return 1;
				else if (((Date) arg0).getTime() < ((Date) arg1).getTime())
					return -1;
				else
					return 0;
			}
		});
		return list;
	}

	/**
	 * 根据传入日期返回星期几
	 * 
	 * @param date
	 *            日期
	 * @return 1-7 1：星期天,2:星期一,3:星期二,4:星期三,5:星期四,6:星期五,7:星期六
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 返回YYYY-MM-DD日期类型，将时分秒格式成00:00:00
	 * 
	 * @param date
	 *            Date类型
	 * @return
	 * @throws ParseException
	 */
	public static Date getSampleDate(Date date) throws ParseException {
		Date returnDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		returnDate = sdf.parse(sdf.format(date));
		return returnDate;
	}

	/**
	 * 返回YYYY-MM-DD日期类型，将时分秒格式成00:00:00
	 * 
	 * @param date
	 *            Date类型
	 * @return
	 * @throws ParseException
	 */
	public static String getDateToStr(Date date) {
		if (date != null) {
			String returnDate;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf.format(date);
			return returnDate;
		}
		return "";
	}

	/**
	 * 返回yyyy-MM-dd HH:mm:ss 日期类型
	 * 
	 * @param date
	 *            Date类型
	 * @return
	 * @throws ParseException
	 */
	public static String getDateToStrMill(Date date) {
		String returnDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		returnDate = sdf.format(date);
		return returnDate;
	}

	/**
	 * 根据pattern返回一定格式的日期类型，
	 * 
	 * @param date
	 *            Date类型
	 * @param pattern
	 *            String类型
	 * @return
	 * @throws ParseException
	 */
	public static Date getSampleDate(Date date, String pattern) throws ParseException {
		Date returnDate;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		returnDate = sdf.parse(sdf.format(date));
		return returnDate;
	}

	/**
	 * 日期月加减运算
	 * 
	 * @param date
	 * @param month
	 *            加减值，"+"表示加，"-"表示减
	 * @return
	 */
	public static Date getDateByMonth(Date date, int month) {
		Date returnDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		returnDate = cal.getTime();
		return returnDate;
	}

	/**
	 * 日期月加减运算
	 * 
	 * @param date
	 * @param month
	 *            加减值，"+"表示加，"-"表示减
	 * @return
	 */
	public static String getDateAddMonth(Date date, int month) {
		Date returnDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		returnDate = cal.getTime();
		return df.format(returnDate);
	}

	/**
	 * 日期日加减运算
	 * 
	 * @param date
	 * @param day
	 *            加减值，"+"表示加，"-"表示减
	 * @return
	 */
	public static Date getDateAddSub(Date date, int day) {
		Date returnDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		returnDate = cal.getTime();
		return returnDate;
	}
	

	/**
	 * 日期日加减运算
	 * 
	 * @param date
	 * @param day
	 *            加减值，"+"表示加，"-"表示减
	 * @return
	 */
	public static String getDateAddDay(Date date, int day) {
		Date returnDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		returnDate = cal.getTime();
		return df.format(returnDate);
	}

	/**
	 * 日期日加减运算
	 * 
	 * @param min
	 * @param min
	 *            加减值，"+"表示加，"-"表示减
	 * @return
	 */
	public static String getDateAddMin(Date date, int min) {
		Date returnDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, min);
		returnDate = cal.getTime();
		return df.format(returnDate);
	}

	public static Date parseDate(Object o) {
		if (o == null) {
			return null;
		}
		String className = o.getClass().getName();
		if ("java.util.Date".equals(className)) {
			return ((Date) o);
		}

		if ("java.sql.Date".equals(className)) {
			return parseDate((java.sql.Date) o);
		}
		if ("java.sql.Time".equals(className)) {
			return parseDate((java.sql.Time) o);
		}
		if ("java.sql.Timestamp".equals(className)) {
			return parseDate((java.sql.Timestamp) o);
		}
		return null;

	}

	public static Date parseDate(java.util.Date utilDate) {
		return utilDate;
	}

	public static Date parseDate(java.sql.Date sqlDate) {
		if (sqlDate == null) {
			return null;
		}
		return new java.util.Date(sqlDate.getTime());
	}

	public static Date parseDate(java.sql.Time sqlTime) {

		if (sqlTime == null) {
			return null;
		}
		return new java.util.Date(sqlTime.getTime());
	}

	public static Date parseDate(java.sql.Timestamp sqlTimestamp) {

		if (sqlTimestamp == null) {
			return null;
		}
		return new java.util.Date(sqlTimestamp.getTime());
	}

	/**
	 * 日期年加减运算
	 * 
	 * @param date
	 * @param day
	 *            加减值，"+"表示加，"-"表示减
	 * @return
	 */
	public static String getDateAddYear(Date date, int year) {
		Date returnDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		returnDate = cal.getTime();
		return df.format(returnDate);
	}

	/**
	 * 日期年加减运算
	 * 
	 * @param date
	 * @param day
	 *            加减值，"+"表示加，"-"表示减
	 * @return 只返回年份
	 */
	public static String getYear(Date date, int year) {
		Date returnDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		returnDate = cal.getTime();
		return df.format(returnDate);
	}

	/**
	 * 日期年加减运算
	 * 
	 * @param date
	 * @param day
	 *            加减值，"+"表示加，"-"表示减
	 * @return 只返回年份
	 */
	public static int getYearInt(Date date, int year) {
		Date returnDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		returnDate = cal.getTime();
		return Integer.parseInt(df.format(returnDate));
	}


	/**
	 * 根据周数获取这一年中第 week 周的 第一天的日期
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getDateByWeekoff(int year, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, Calendar.JANUARY, 1);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (week > 1)
			calendar.add(Calendar.DATE, 7 * week - dayOfWeek);
		return CalendarUtil.getWeekStart(calendar.getTime());
	}
	
	/**
	 * 获取当前日期上周(星期一)
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastWeekStart() {
		Calendar cal = Calendar.getInstance();
		 //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		 int n = 1;
		cal.add(Calendar.DATE, -n*7);
		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当前日期上周(星期天)
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastWeekEnd() {
		Calendar cal = Calendar.getInstance();

		 int n = 0;
		cal.add(Calendar.DATE, -n*7);
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 获取当前日期起始周(星期一)
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getWeekStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, -day + 2);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}



	/**
	 * 获取当前日期周末(星期六)
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, 6 - day + 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 计算两日期相差的小时数
	 * 
	 * @param date
	 * @param date
	 * @return 小时数
	 */
	public static double getHours(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000.00 * 60.00 * 60.00);
	}

	/**
	 * 根据pattern返回一定格式的日期字符串，
	 * 
	 * @param date
	 *            Date类型
	 * @param pattern
	 *            String类型
	 * @return
	 * @throws ParseException
	 */
	public static String getFormatDate(Date date, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date getFormatDate(String str) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public static int getAge(Date birthday) {
		Calendar calendar = Calendar.getInstance();
		Calendar tempbirthday = Calendar.getInstance();
		tempbirthday.setTime(birthday);
		// 获取当前时间毫秒值
		long now = (new Date()).getTime();
		long time = now - birthday.getTime();
		int count = 0;
		// 时间换算
		long days = time / 1000 / 60 / 60 / 24;
		// 判断闰年
		int birthYear = tempbirthday.get(Calendar.YEAR);
		for (int i = calendar.get(Calendar.YEAR); i >= birthYear; i--)
			if ((i % 4 == 0 && !(i % 100 == 0)) || (i % 400 == 0)) {
				count++;
			}
		// 加入闰年因素进行整理换算
		int age = ((int) days - count) / 365;
		return age;
	}

}
