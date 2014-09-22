package com.heracles.framework.tools;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatJson {

	private static ObjectMapper mapper;
	private static Logger logger = LoggerFactory.getLogger(FormatJson.class);
	
	public static String listToJson(List<?> list){
		mapper = new ObjectMapper();  
        StringWriter sw = new StringWriter();  
        try {
			mapper.writeValue(sw, list);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} 
		return sw.toString();
	}
	
	public static String mapToJson(Map<?,?> map){
		mapper = new ObjectMapper();  
        StringWriter sw = new StringWriter();  
        try {
			mapper.writeValue(sw, map);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} 
		return sw.toString();
	}
	
}
