package com.heracles.framework.general;

import com.heracles.framework.service.Language;

public class GeneralToken {
	
	private static boolean isUserLoginLog = true;
	private static boolean isRegister = true;
	private static boolean isAutoRunTask = true;
	private static boolean isTimedScanTask = false;
	private static int language = Language.CHINA;
	private static int clearSpace = 3;
	private static String encoder = "UTF-8";

	public static void setUserLoginLog(boolean isLog){
		isUserLoginLog = isLog;
	}
	
	public static boolean getUserLoginLog(){
		return isUserLoginLog;
	}

	public static void setRegister(boolean _register){
		isRegister = _register;
	}
	
	public static boolean getRegister(){
		return isRegister;
	}
	
	public static void setLanguage(int _language){
		language = _language;
	}
	
	public static int getLanguage(){
		return language;
	}
	
	public static void setClearSpace(int space){
		clearSpace = space;
	}
	
	public static int getClearSpace(){
		return clearSpace;
	}
	
	public static String getEncoder(){
		return encoder;
	}
	
	public static void setEncoder(String _encoder){
		encoder = _encoder;
	}
	
	public static void setIsAutoRunTask(boolean isRun){
		isAutoRunTask = isRun;
	}
	
	public static boolean getIsAutoRunTask(){
		return isAutoRunTask;
	}
	
	public static void setIsTimedScanTask(boolean isRun){
		isTimedScanTask = isRun;
	}
	
	public static boolean getIsTimedScanTask(){
		return isTimedScanTask;
	}
	
	
}
