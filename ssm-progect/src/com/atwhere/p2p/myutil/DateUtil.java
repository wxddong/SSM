package com.atwhere.p2p.myutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
	
	public static final String Y_M_D_H_M_S_S = "yyyy-MM-dd HH:mm:ss.S";
	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	public static final String Y_M_D = "yyyy-MM-dd";
	public static final String H_M_S = "HH:mm:ss";
	public static final String H_M_S_S = "HH:mm:ss.S";
	
	/**
	 * 获取系统当前的时间
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 *得到系统当前月份
	 */
	@SuppressWarnings("static-access")
	public static int getMonthOfCurrentDate() {
		Calendar rightNow = Calendar.getInstance();
		int month = rightNow.get(rightNow.MONTH) + 1;
		return month;
	}
	
	/**
	 * 获取formatStr格式的系统当前时间
	 * @param formatStr:指定格式的字符串
	 * @return Date：返回的指定格式的日期
	 */
	public static Date getCurntDate(String formatStr) {
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
			Date date = dateFormat.parse(dateFormat.format(c.getTime()));			
			return date;
		} catch (Exception e) {
			return new Date();
		}
	}
	
	/**
	 * 获取formatStr格式的系统当前时间字符串
	 * @param formatStr : 指定格式的字符串
	 * @return String : 指定格式的日期字符串
	 */
	public static String getCurntDateStr(String formatStr) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
			String dateStr = dateFormat.format(c.getTime());
			return dateStr;
	}
	
	
	/**
	 * 将指定的日期转化为指定格式的日期
	 * @param date : 要转化的日期
	 * @param formatStr ： 要将将日期转化的格式
	 * @return Date ： 转化为指定格式后的日期
	 */
	public static Date formatToDate(Date date,String formatStr){
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		try {
			return  dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	/**
	 * 将指定的日期转化为指定格式的日期字符串
	 * @param date : 要转化的日期
	 * @param formatStr ： 要将将日期转化的格式
	 * @return String ： 转化为指定格式后的日期字符串
	 */
	public static String formatToDateStr(Date date,String formatStr){
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		return  dateFormat.format(date);
	}
	
	/**
	 * 将字符串转化为指定格式的日期
	 * @param dateStr ： 要转化为日期的字符串
	 * @param formatStr ： 指定的格式
	 * @return Date：指定格式的日期
	 * @throws Exception
	 */
	public static Date stringToDate(String dateStr,String formatStr) throws Exception{
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(formatStr);
			date = df.parse(dateStr);
			return date;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 *将一个日期转化成Calendar
	 */
	public static Calendar StringToCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 *将一个日期字符串转化成Calendar
	 */
	public static Calendar StringToCalendar(String sDate,String formatStr) {
		Date date = null;
		try {
			date = stringToDate(sDate,formatStr);
		} catch (Exception ex) {
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	
	/**
	 * 获取当前时间 n天之前的时间   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return Date : 当前时间前n天的时间
	 */
	public static Date getNDayBeforeCurrentDate(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_MONTH, -n);
		return formatToDate(c.getTime(), formatStr);
	}
	
	
	/**
	 * 获取当前时间 n天之前的时间字符串   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return String : 当前时间前n天的时间字符串
	 */
	public static String getNDayBeforeCurrentDateStr(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_MONTH, -n);
		return formatToDateStr(c.getTime(), formatStr);
	}
	
	/**
	 *取得系统当前时间前n天的日期集合,格式为formatStr
	 */
	public static List<String> getNDayBeforeCurrentDateStrList(int n,String formatStr) {
		String day;
		List<String> list=new ArrayList<String>();
		for(int i=0;i<n;i++){
			day=DateUtil.getNDayBeforeCurrentDateStr(i,formatStr);
			list.add(day);
		}
		return list;
	}
	
	/**
	 * 获取当前时间 n天之后的时间   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return Date : 当前时间后n天的时间
	 */
	public static Date getNDayAfterCurrentDate(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_MONTH, n);
		return formatToDate(c.getTime(), formatStr);
	}
	
	
	/**
	 * 获取当前时间 n天之后的时间字符串   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return String : 当前时间后n个天的时间字符串
	 */
	public static String getNDayAfterCurrentDateStr(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_MONTH, n);
		return formatToDateStr(c.getTime(), formatStr);
	}
	
	
	/**
	 * 获取当前时间 n个月之前的时间   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return Date : 当前时间前n个月的时间
	 */
	public static Date getNMonthBeforeCurrentDate(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, -n);
		return formatToDate(c.getTime(), formatStr);
	}
	
	
	/**
	 * 获取当前时间 n个月之前的时间字符串   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return String : 当前时间前n个月的时间字符串
	 */
	public static String getNMonthBeforeCurrentDateStr(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, -n);
		return formatToDateStr(c.getTime(), formatStr);
	}
	
	/**
	 * 获取当前时间 n个月之后的时间   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return Date : 当前时间后n个月的时间
	 */
	public static Date getNMonthAfterCurrentDate(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, n);
		return formatToDate(c.getTime(), formatStr);
	}
	
	
	/**
	 * 获取当前时间 n个月之后的时间字符串   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return String : 当前时间后n个月的时间字符串
	 */
	public static String getNMonthAfterCurrentDateStr(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, n);
		return formatToDateStr(c.getTime(), formatStr);
	}
	
	/**
	 * 获取当前时间 n个年之前的时间   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return Date : 当前时间前n个年的时间
	 */
	public static Date getNYearBeforeCurrentDate(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.YEAR, -n);
		return formatToDate(c.getTime(), formatStr);
	}
	
	
	/**
	 * 获取当前时间 n个年之前的时间字符串   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return String : 当前时间前n个年的时间字符串
	 */
	public static String getNYearBeforeCurrentDateStr(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.YEAR, -n);
		return formatToDateStr(c.getTime(), formatStr);
	}

	/**
	 * 获取当前时间 n个年之后的时间   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return Date : 当前时间后n个年的时间
	 */
	public static Date getNYearAfterCurrentDate(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.YEAR, n);
		return formatToDate(c.getTime(), formatStr);
	}
	
	
	/**
	 * 获取当前时间 n个年之后的时间字符串   格式为formatStr
	 * @param n
	 * @param formatStr : 格式
	 * @return String : 当前时间后n个年的时间字符串
	 */
	public static String getNYearAfterCurrentDateStr(int n,String formatStr) {
		Calendar c = Calendar.getInstance();
		c.add(c.YEAR, n);
		return formatToDateStr(c.getTime(), formatStr);
	}
	
	/**
	 * 时间格式化输出    2017年3月20日 星期一
	 * @param date
	 * @return
	 */
	public static String formatDatePrint(Date date){
		return DateFormat.getDateInstance(DateFormat.FULL).format(date);
	}
	
	/**
	 * 比较两个字符串时间大小
	 * @param fromDate
	 * @param toDate
	 * @param formatStr
	 * @return boolean :fromDate早于toDate return true
	 * 			        fromDate晚于toDate return false
	 */
	public static boolean compareTwoDateStr(String fromDate,String toDate,String formatStr) {
		Date dateFrom = null;
		try {
			dateFrom = stringToDate(fromDate,formatStr);
		} catch (Exception ex) {
		}
		Date dateTo = null;
		try {
			dateTo = stringToDate(toDate,formatStr);
		} catch (Exception ex1) {
		}
		if (dateFrom.before(dateTo)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * @Description: 比较两个日期大小
	 * @param fromDate
	 * @param toDate
	 * @param formatType
	 * @return  fromDate等于toDate return  0
	 * 			fromDate早于toDate return -1
	 * 			fromDate晚于toDate return  1
	 * 2014-10-28
	 */
	public static int compareTwoDateStr1(String fromDate,String toDate,String formatStr){
		Date dateFrom = null;
		try {
			dateFrom = stringToDate(fromDate,formatStr);
		} catch (Exception ex) {
		}
		Date dateTo = null;
		try {
			dateTo = stringToDate(toDate,formatStr);
		} catch (Exception ex1) {
			
		}
		return dateFrom.compareTo(dateTo);
	}
	
	
	/**
	 *判断日期是否是瑞年
	 */
	public static boolean isRunNian(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		int year = c.get(c.YEAR);
		if (0 == year % 4 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	 /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
	public static int dayForWeek(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = 7;
		}else{
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	   /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
	public static String dayStrForWeek(String date,String formatStr){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			Date bTime = sdf.parse(date);
			c.setTime(bTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String dayForWeek = "" ;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = "周日";
		}else{
			dayForWeek = ((c.get(Calendar.DAY_OF_WEEK) - 1)+"").replace("1", "周一")
			.replace("2", "周二").replace("3", "周三")
			.replace("4", "周四").replace("5", "周五")
			.replace("6", "周六");
		}
		return dayForWeek;
	}


	
}
