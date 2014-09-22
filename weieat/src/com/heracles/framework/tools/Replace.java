package com.heracles.framework.tools;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 字符串替换工具.
 * 
 * @author yinzj
 */

public class Replace {

	public static String replace(String content, Map<String, String> replaceMap){
		if (replaceMap == null) return content;
        Set<String> key = replaceMap.keySet();
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
        	String s = it.next().toString();
        	content = replaceLongStr(content, s, replaceMap.get(s));
        }
		return content;
	}
	
	public static String replaceLongStr(String str, String fromStr, String toStr) {
	    StringBuffer result = new StringBuffer();
	    if (str != null && !str.equals("")) {
	      while (str.indexOf(fromStr) > 0) {
	        result.append(str.substring(0, str.indexOf(fromStr)));
	        result.append(toStr);
	        str = str.substring(str.indexOf(fromStr)+fromStr.length(), str.length());
	      }
	      result.append(str);
	    }
	    return result.toString();
	}
	
	public static String format(String xmlContent){
		if (xmlContent != null && !xmlContent.trim().equals("")){
			xmlContent = xmlContent.replace("\n", "");
			//xmlContent = xmlContent.replace(" ", "");
			xmlContent = xmlContent.replace("\t", "");
		}
		return xmlContent;
	}
	
	public static String replaceLineBreak(String s){
		s = s.replace("\n", "");
		return s;
	}

	
}
