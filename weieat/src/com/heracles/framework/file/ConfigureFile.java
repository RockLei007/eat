package com.heracles.framework.file;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heracles.framework.tools.Unit;



/*
 * 配置文件操作类
 * @author yinzhijian 
 */
public class ConfigureFile {
/*
 * 
 */
	private Properties pr;
	private String path;
	final Logger logger = LoggerFactory.getLogger(getClass());

/*
 * 
*/ 
	public static ConfigureFile getInstance(String fileName){
		String path = Unit.getWebInfPath();
		return new ConfigureFile(path, fileName);
	}	
	
/*
 * 
 */	
	public static ConfigureFile getInstance(String path, String fileName){
		return new ConfigureFile(path, fileName);
	}

/*
 * 	
 */
	public String getLine(String key){
		return pr.getProperty(key);
	}

/*
 * 	
 */
	public Map<String, String> getAllLine(){
		Map<String, String> keyMap = null; 
		if (pr.size() > 0){
			keyMap = new HashMap<String, String>();
			for (Enumeration<Object> s = pr.keys(); s.hasMoreElements();){
				String key = (String) s.nextElement();
				keyMap.put(key, (String) pr.get(key));
			}
		}
		return keyMap;		
	}
	
/*
 * 	
 */
	public void setValue(String key, String value){
		pr.setProperty(key, value);
	}	
	
/*
 * 	
 */	
	public void save(){
		try {
			pr.store(new FileOutputStream(this.path), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/*
 * 	
 */
	private ConfigureFile(String path, final String fileName){
		this.path = Unit.replacePath(path)+fileName;
		pr = new Properties();
		try {
			pr.load(new FileInputStream(this.path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void release(){
		pr.clear();
		pr = null;
	}
	
}
