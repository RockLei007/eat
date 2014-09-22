package com.heracles.framework.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heracles.framework.tools.Unit;


/**
 * 文件操作类
 * 
 * @author yinzj 
 */
public class ReadFile {
/*
 * 
 */
	Logger logger = LoggerFactory.getLogger(getClass());
	private String fileName = "";
	private String path = "";
	private BufferedReader reader = null;
	public final static boolean REPLACE_CONTENT = false;
	public final static boolean APPEND_CONTENT = true;

/*
 * 
*/ 
	public static ReadFile getInstance(){
		return new ReadFile();
	}	
	
/*
 * 
*/ 
	public static ReadFile getInstance(String fileName){
		String path = Unit.getClassesPath();
		return new ReadFile(path, fileName);
	}	
	
/*
 * 
 */	
	public static ReadFile getInstance(String path, String fileName){
		return new ReadFile(path, fileName);
	}

/*
 * 	
 */
	public String getContent(){
		StringBuffer sb = new StringBuffer();
		String tempString = null;
		try {
			while((tempString = reader.readLine()) != null){
				sb.append(tempString+'\n');
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return sb.toString();
	}

/*
 * 	
 */
	public void write(String path, String fileName, String content, boolean type){
		try {
		    //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		    FileWriter writer = new FileWriter(path + fileName, type);
		    writer.write(content);
		    writer.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}	
	
/*
 * 	
 */
	public void write(String content, boolean type){
		if (Unit.isNotNull(this.path) && Unit.isNotNull(this.fileName)){
			write(this.path, this.fileName, content, type);
		}else{
			logger.error("file path and name is null!");
		}
	}	

/*
 * 	
 */
	public void release(){
		if (reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			reader = null;
		}
	}
	
/*
 * 	
 */
	private ReadFile(String path, final String fileName){
		this.path = Unit.replacePath(path);
		this.fileName = fileName;
		File file = new File(path+fileName);
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

/*
 * 	
 */
	private ReadFile(){
	}
	
	
}
