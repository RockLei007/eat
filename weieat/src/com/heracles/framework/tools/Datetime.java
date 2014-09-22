package com.heracles.framework.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datetime {

/*
 * 按"yyyy-MM-dd HH:mm:ss"格式得到当前时间
 */
	public static String getNow(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(System.currentTimeMillis());
	}

/*
 *按"yyyyMMddHHmmss"格式得到当前时间
 */	
	public static String getShortNow(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(System.currentTimeMillis());
	}

/*
 * 得到过去几年或者几月或者几天的时间
 */		
	public static String getPassBy(int year, int month, int day){
		if (day > 30 || month > 12 || year > 99) return "";
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -year);
		c.add(Calendar.MONTH, -month);
		c.add(Calendar.DATE, -day);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		return df.format(c.getTime());
	}
	
/*
 * 在当前时间下增加小时、分钟、秒
 */		
	public static Long getFutureTime(int hour, int minute, int second){
		if (hour > 24 || minute > 60 || second > 60) return 0L;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, +hour);
		c.add(Calendar.MINUTE, +minute);
		c.add(Calendar.SECOND, +second);
		return c.getTimeInMillis();
	}

/*
 * 在指定的时间下增加小时、分钟、秒
 */		
	public static Long getFutureTime(Long datetime, int hour, int minute, int second){
		if (hour > 24 || minute > 60 || second > 60) return 0L;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(datetime);
		c.add(Calendar.HOUR, +hour);
		c.add(Calendar.MINUTE, +minute);
		c.add(Calendar.SECOND, +second);
		return c.getTimeInMillis();
	}	
	
/*
 * 在当前时间下增加小时、分钟、秒
 */		
	public static Long getLastTime(int hour, int minute, int second){
		if (hour > 24 || minute > 60 || second > 60) return 0L;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, -hour);
		c.add(Calendar.MINUTE, -minute);
		c.add(Calendar.SECOND, -second);
		return c.getTimeInMillis();
	}

/*
 * 在指定的时间下增加小时、分钟、秒
 */		
	public static Long getLastTime(Long datetime, int hour, int minute, int second){
		if (hour > 24 || minute > 60 || second > 60) return 0L;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(datetime);
		c.add(Calendar.HOUR, -hour);
		c.add(Calendar.MINUTE, -minute);
		c.add(Calendar.SECOND, -second);
		return c.getTimeInMillis();
	}		
	

/*
 * 将长整型的时间转换为字符型的时间，格式为："yyyy-MM-dd hh:mm:ss"
 */		
	public static String LongFormat(Long time){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.format(time);
	}

/*
 * 将格式为："yyyy-MM-dd hh:mm:ss"的字符型的时间转换为长整型的时间
 */		
	public static Long StringFormat(String datetime){
		return StringToDate(datetime).getTime();
	}

	public static Date StringToDate(String datetime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try {
			date = df.parse(datetime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(System.currentTimeMillis());
	}

}
