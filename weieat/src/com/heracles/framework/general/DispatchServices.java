package com.heracles.framework.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;


public class DispatchServices {

	private static HttpSession session;
	private static HttpServletRequest request;
	private static WebApplicationContext webAppContext;
	
	public static void setRequest(HttpServletRequest _request){
		request = _request;
		session = request.getSession();
	}
	
	//不建议使用的方法，注入一次后将不更新
	@Deprecated
	public static HttpServletRequest getRequest(){
		return request;
	}
	
	public static HttpSession getSession(){
		return session;
	}
	
	public static Object getContext(String attribute) {
		return session.getAttribute(attribute);
	}
	
	public static void setContext(String attribute, Object object) {
		session.setAttribute(attribute, object);
	}
		
	public static String getUserIp(){
		return request.getRemoteAddr();
	}
	
	public static WebApplicationContext getWebAppContext(){
		return webAppContext;
	}
	
	public static void setWebAppContext(WebApplicationContext _webAppContext){
		webAppContext = _webAppContext;
	}
	
}
