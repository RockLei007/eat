package com.heracles.framework.xml;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.heracles.framework.tools.Unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取xml文件操作类.
 * 
 * @author yinzj
 */


public class ReadXMLFile extends AbstractReadXML{

	Element element;
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static ReadXMLFile getTemplateInstance(String templateName){
		return new ReadXMLFile(Unit.getRootPath() + "template/" +templateName);
	}
	
	public static ReadXMLFile getInstance(String path, String fileName){
		return new ReadXMLFile(path + fileName);
	}
	
	public static ReadXMLFile getClassInstance(String fileName){
		return new ReadXMLFile(Unit.getClassesPath() + fileName);
	}
	
	private ReadXMLFile(String fileName) {
		setFileName(fileName);
		parse();
	}

	public void parse() {
		try {
			document = reader.read(file + ".xml");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
