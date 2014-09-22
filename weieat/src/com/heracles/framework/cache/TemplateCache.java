package com.heracles.framework.cache;

import java.util.HashMap;
import java.util.Map;

public class TemplateCache {

	private static final Map<String, String> xmlTemplateMap = new HashMap<String, String>();
	private static final Map<String, String> htmlTemplateMap = new HashMap<String, String>();
	
	public static void setXmlTemplate(String templateName, String content){
		xmlTemplateMap.put(templateName, content);
	}
	
	public static String getXmlTempLate(String templateName){
		return xmlTemplateMap.get(templateName);
	}
	
	public static void setHtmlTemplate(String templateName, String content){
		htmlTemplateMap.put(templateName, content);
	}
	
	public static String getHtmlTempLate(String templateName){
		return htmlTemplateMap.get(templateName);
	}
	
}
