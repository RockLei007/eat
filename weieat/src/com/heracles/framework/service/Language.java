package com.heracles.framework.service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heracles.framework.general.GeneralToken;

/**
 * 全局语言类,操作WEB-INF\classe\message_zh_CN.properties.
 * 
 * @author yinzj
 */

public class Language {

	public final static int ENGLISH = 0;
	public final static int CHINA = 1;	
	private static Locale l = null;
	private static ResourceBundle rb;
	private static Logger logger = LoggerFactory.getLogger(Language.class);
	
	private static void init(){
		if (GeneralToken.getLanguage() == CHINA){
			l = Locale.CHINA;
		}else
		if (GeneralToken.getLanguage() == ENGLISH){
			l = Locale.ENGLISH;
		}else
			l = Locale.getDefault(); 
		//其中"message"在struts.xml中也要有相应的配置
		rb = ResourceBundle.getBundle("message",l); 
	}
		
	public static String getMessage(String lable){
		init();
		String message = "";
		try{
			message = rb.getString(lable);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	    return message; 
	}
	
	public static String getMessage(String lable, String[] parameter){
		init();
		String message = "";
		try{
			MessageFormat format = new MessageFormat(rb.getString(lable));
			message = format.format(parameter);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return message;
	}
	
}
