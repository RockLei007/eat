package com.heracles.framework.xml;

import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取xml字符串类.
 * 
 * @author yinzj
 */

public class ReadXMLStream extends AbstractReadXML{

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ReadXMLStream(){
		parse();
	}
	
	public static ReadXMLStream getInstance(String content) {
		if (content != null && !content.trim().equals("")) 
			setXMLContent(content);
		return new ReadXMLStream();
	}

	public String readNodeText(String node1Name, String node2Name){
		Element rootElm = document.getRootElement(); 
		Element childElm = rootElm.element(node1Name); 
		if (childElm != null){
			Element child1Elm = childElm.element(node2Name);
			if (child1Elm != null){
				String s = "";
				s = child1Elm.getText();
				return s;
			}else
				return null;
		}else{
			return null;
		}
	}
	
	public String analyze(List<?> nodeNameList, String[] parameter){
		StringBuffer sb = new StringBuffer();
		Element rootElm = document.getRootElement(); 
		if (rootElm != null){
			int j = 0;
			getNext(rootElm, sb, nodeNameList, j, parameter);
		}
		return sb.toString();
	}
	
	private void getNext(Element element, StringBuffer sb, List<?> nodeNameList, int j, String[] parameter){
		Element temp = null;
		for (Iterator<?> iter = element.elementIterator(); iter.hasNext(); ) {
			temp = (Element) iter.next(); 
			if (temp != null){
				for (int n = 0; n < parameter.length; n++){
					if (temp.getName().equals(parameter[n])){
						parameter[n] = temp.getText().trim();
					}
				}
				for(int i = 0; i < nodeNameList.size(); i++){
					if (temp.getName().equals(nodeNameList.get(i))){
						sb.append(temp.getText().trim());
						sb.append("@");
						j++;
					}
				}
				if (j == nodeNameList.size()){ 
					sb.append("#");
					j = 0;
				}
				getNext(temp, sb, nodeNameList,j,parameter);
			}
		}
	}
	
	public void parse() {
		try {
			document = DocumentHelper.parseText(xmlContent);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}


}
