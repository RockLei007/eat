package com.heracles.framework.tools;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJson {

	private static ObjectMapper mapper; 
	private static Logger logger = LoggerFactory.getLogger(ReadJson.class);
	

	public static Object readJson2Pojo(Class<?> pojoClass, String jsonString){
		Object obj = null;
		if (Unit.isNotNull(jsonString)){
			mapper = new ObjectMapper(); 
			try {
				obj = mapper.readValue(jsonString, pojoClass);
			} catch (JsonParseException e) {
				logger.error(e.getMessage());
			} catch (JsonMappingException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}  
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static List<LinkedHashMap<String, Object>> readJson2List(String jsonString){
		List<LinkedHashMap<String, Object>> list = null;
		if (Unit.isNotNull(jsonString)){
			mapper = new ObjectMapper(); 
			try {
				list = mapper.readValue(jsonString, List.class);
			} catch (JsonParseException e) {
				logger.error(e.getMessage());
			} catch (JsonMappingException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}  
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, Object>> readJson2Map(String jsonString){
		Map<String, Map<String, Object>> map = null;
		if (Unit.isNotNull(jsonString)){
			mapper = new ObjectMapper(); 
			try {
				map = mapper.readValue(jsonString, Map.class);
			} catch (JsonParseException e) {
				logger.error(e.getMessage());
			} catch (JsonMappingException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}  
		}
		return map;
	}
	
	
}
