package com.heracles.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.heracles.framework.cache.TemplateCache;
import com.heracles.framework.xml.ReadXMLStream;

public class Rule {
	
	private static String ruleXML = TemplateCache.getXmlTempLate("rule");
	private static ReadXMLStream stream = ReadXMLStream.getInstance(ruleXML);;
	private static Map<String, List<RuleValue>> business = null;
	private static List<RuleValue> ruleList = null;
	

	public static List<RuleValue> getAllRule(){
		if (ruleList == null){
			parse();
		}
		return ruleList;
	}
	
	public static Map<String, List<RuleValue>> getMapRule(){
		if (ruleList == null){
			parse();
		}
		return business;
	}
	
	private static void parse(){
		Iterator<Element> iter = stream.readNode("business");
		business = new HashMap<String, List<RuleValue>>();
		ruleList = new ArrayList<RuleValue>();
		while (iter.hasNext()){
			Element ele = iter.next();
			String name = ele.attributeValue("name").trim();
			Iterator<Element> childIter = ele.elementIterator("rule");
			List<RuleValue> valueList = new ArrayList<RuleValue>();
			while (childIter.hasNext()){
				Element chileEle = childIter.next();
				RuleValue value = new RuleValue();
				value.setClassName(chileEle.attributeValue("class").trim());
				value.setRegex(chileEle.attributeValue("regex").trim());
				value.setName(chileEle.attributeValue("name").trim());
				String type = chileEle.attributeValue("return_type").trim();
				value.setReturnType(type);
				if (type.equals("news")){
					value.setTitle(chileEle.elementText("title"));
					value.setDescription(chileEle.elementText("description"));
					value.setPicUrl(chileEle.elementText("picUrl"));
					value.setUrl(chileEle.elementText("url"));
				}
				if (type.equals("text")){
					value.setContent(chileEle.getText());
				}
				if (type.equals("music")){
					value.setTitle(chileEle.elementText("title"));
					value.setDescription(chileEle.elementText("description"));
					value.setMusicUrl(chileEle.elementText("musicUrl"));
					value.setHqMusicUrl(chileEle.elementText("hqMusicUrl"));
				}
				valueList.add(value);
				ruleList.add(value);
			}
			business.put(name, valueList);
		}
	}
	
	
}
