package com.heracles.framework.xml;

/**
 * xml文件操作父类
 * 
 * @author yinzj
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public abstract class AbstractReadXML implements ReadXML {
	
	protected SAXReader reader = new SAXReader();
	protected Document document;
	protected static String file; 
	protected static String xmlContent;

	
	public void setFileName(String fileName){
		file = fileName;
	}

	public static void setXMLContent(String content){
		xmlContent = content;
	}
	
	public List<String> read(String nodeName, String keyWord){
		Element rootElm = document.getRootElement();
		Element root1Elm = null;
		List<String> list = null;
		if (rootElm != null) {
			root1Elm = rootElm.element(nodeName);
			if (root1Elm != null){
				list = new ArrayList<String>(); 
				for (Iterator<Element> iterInner = root1Elm.elementIterator(); iterInner.hasNext(); ) {
		              Element elementInner = iterInner.next();
		              if(elementInner.getName().equals(keyWord)){
		            	  list.add(elementInner.getText());
		              }
				}
			}
		}
		return list;
	}
	
	public Iterator<Element> readNode(String keyWord){
		Iterator<Element> iterInner = null;
		Element rootElm = document.getRootElement();
		if (rootElm != null){
			iterInner = rootElm.elementIterator(keyWord);
			if (iterInner != null)
				return iterInner;
		}
		return null;
	}
	
	//path case : ReturnInfo/userlist/item
	public List<Element> readPath(String path){
		return document.selectNodes(path);
	}
	
	public String read(String keyWord){
		Element childElm = null;
		String result = "";
		Element rootElm = document.getRootElement();
		if (rootElm != null){
			childElm = rootElm.element(keyWord);
			if (childElm != null)
				result = childElm.getText();
		}
		return result;
	}
	
	public String getContent(){
		Element rootElm = document.getRootElement();
		return rootElm.asXML();
	}
	
	
}
