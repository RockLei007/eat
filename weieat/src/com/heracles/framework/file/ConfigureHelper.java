package com.heracles.framework.file;

import java.util.HashMap;
import java.util.Map;

public class ConfigureHelper {
	
	private static Map<String, String> configMap = new HashMap<String, String>();
	
	public static void setconfigMap(Map<String, String> map){
		configMap = map;
	}
	
	public static String getValue(String key){
		return configMap.get(key);
	}

}
