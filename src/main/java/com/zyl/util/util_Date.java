package com.zyl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期转换、比较工具类
 * @author Yulin
 *
 */
public class util_Date {
	public static final SimpleDateFormat Format_datetime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat Format_date = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat Format_time = new SimpleDateFormat(
			"HH:mm:ss");
	public enum dillType {  
	    DAY,HOUR,MIN,SEC
	}
	
	/**
	 * 利用日期类获取对应的年月日信息
	 */
	public static  int getYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	public static int getMonth(){
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	public static int getCurrentDate(){
		return Calendar.getInstance().get(Calendar.DATE);
	}
	/**
	 * return Day of Week
	 */
	public static int getDoW(){
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	/**
	 *  return Day of Month
	 */
	public static int getDoM(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	/**
	 *  return Day of Year
	 */
	public static int getDoY(){
		return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	}
	
	
	public static Date date2date(Date date,SimpleDateFormat whatFormat) throws Exception{
		if(whatFormat==null||whatFormat.equals("")){
			throw new Exception("formatType值不可为空");
		}
		return whatFormat.parse(whatFormat.format(date));
	}
	public static String string2string(String date,SimpleDateFormat whatFormat) throws Exception{
		if(whatFormat==null||whatFormat.equals("")){
			throw new Exception("formatType值不可为空");
		}
		return whatFormat.format(whatFormat.parse(date));
			
	}
	public static Date string2date(String date,SimpleDateFormat whatFormat) throws Exception{
		if(whatFormat==null||whatFormat.equals("")){
			throw new Exception("formatType值不可为空");
		}
		return whatFormat.parse(date);
	}
	public static String date2string(Date date,SimpleDateFormat whatFormat) throws Exception{
		if(whatFormat==null||whatFormat.equals("")){
			throw new Exception("formatType值不可为空");
		}
		return whatFormat.format(date);
	}
	
	
	/**
	 * 将当前系统日期于传入日期对比:格式[yyyy-MM-dd]
	 * @param date
	 * @return String
	 * @throws ParseException
	 */
	public static String compareJobDate( Date date) throws ParseException {
//		System.out.println(starDate);
		//系统当前年月日
		Date now=new Date();
		now=Format_date.parse(Format_date.format(now));
		//如果起始日期等于或晚于今日
		if(date.before(now)){
			return "before";
		}else if(date.equals(now)){
			return "equals";
		}else{
			return "after";
		}

	}
	
	/**
	 * 将当前系统日期于传入时间对比:格式"HH:mm:ss" 或"HH:mm"
	 * @param dateTime
	 * @return
	 * @throws ParseException
	 */
	public static String compareJobTime(String dateTime) throws ParseException {
		if(dateTime.length()<6){
			dateTime+=":00";
		}
		//将传入时间转为date类型
		Date date_Time=Format_time.parse(dateTime);
		System.out.println(date_Time);
		//系统当前时间
		Date now=new Date();
		now=Format_time.parse(Format_time.format(now));
		System.out.println(now);
		//如果起始日期等于或晚于今日
		if(date_Time.before(now)){
			return "before";
		}else if(date_Time.equals(now)){
			return "equals";
		}else{
			return "after";
		}
	}
	
    public static long dateDiff(String startTime, String endTime,SimpleDateFormat whatFormat,dillType type)
			throws Exception {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
//		long day = diff / nd;// 计算差多少天
//		long hour = diff % nd / nh;// 计算差多少小时
//		long min = diff % nd % nh / nm;// 计算差多少分钟
//		long sec = diff % nd % nh % nm / ns;// 计算差多少秒
		long l=0;
		long diff;
		if(whatFormat==null||whatFormat.equals("")||type==null||type.equals("")){
			throw new Exception("formatType或type值不可为空");
		}
		//计算时间差
		diff = whatFormat.parse(endTime).getTime() - whatFormat.parse(startTime).getTime();
		switch (type) {
			case DAY:
				l = diff / nd;// 计算差多少天
				break;
			case HOUR:
				l = diff % nd / nh;// 计算差多少小时
				break;
			case MIN:
				l = diff % nd % nh / nm;// 计算差多少分钟
				break;
			case SEC:
				l = diff % nd % nh % nm / ns;// 计算差多少秒
				break;
				
		}
		return l;
	}
	
	public static void main(String[] args) {
		
		System.out.println(util_Date.getYear());
		System.out.println(util_Date.getDoY());
}
}
