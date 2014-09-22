package com.heracles.framework.tools;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/*
 * 公共工具类
 * 
 * @author yinzhijian 
 */
public class Unit {
	
/*
 * 	取得项目的根目录
 */
	public static String getRootPath(){
		Unit unit = new Unit();
		String path = unit.getClass().getResource("/").getPath();
		path = path.substring(0, path.indexOf("WEB-INF"));
		return path;
	}
	
/*
 * 	
 */
	public static String getWebInfPath(){
		Unit unit = new Unit();
		String path = unit.getClass().getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("classes"));
		return path;
	}

/*
 * 	取得项目的class目录
 */
	public static String getClassesPath(){
		Unit unit = new Unit();
		String path = unit.getClass().getResource("/").getPath();
		return path;
	}	
	
/*
 * 	将url encoder字符格式转换为utf-8
 */
	public static String encoderToUTF_8(String content){
		try {
			content = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content; 
	}

/*
 * 	替换Linux和windows下的文件路径中的斜杠
 */
	public static String replacePath(String path){
		String separator = System.getProperty("file.separator");
		if (separator.equals("\\")){
			path = path.replaceFirst("/", "");
			path = path.replace("/", separator);
		}else{
			path = path.replace("\\", separator);
		}
		return path;
	}
	

/*
 * 	判断是否为null或者等于null
 */	
	public static String judge(String s){
		if (s != null && !s.trim().equals("null")){
			return s;
		}else
			return "";
	}

/*
 * 	判断是否为null或者等于""
 */		
	public static boolean isNotNull(String s){
		return (s != null && !s.equals(""));
	}

/*
 * 	判断List是否为null或者size < 0
 */		
	public static boolean isNotNull(List<?> list){
		return (list != null && list.size() > 0);
	}	
	
/*
 * 	转换为url encoder的格式
 */		
	public static String toURLEncoder(String s, String encoder){
		s = judge(s);
		if (!s.equals("")) {
			try {
				s = URLEncoder.encode(s, encoder);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

/*
 * 将list里的序列格式化为字符串	return 'a','b','c'
 */	
	public static String listToStrings(List<String> list){
		StringBuffer sb = new StringBuffer();
		int n = list.size();
		if (list != null && n > 0){
			for (int i = 0; i < n; i++){
				sb.append("'" + list.get(i) + "'");
				if (i < n - 1) sb.append(","); 
			}
		} 
		return sb.toString();
	}

/*
 * 判断一个字符串s是不是在String[]中
 */	
	public static boolean indexOfArray(String[] array, String s){
		boolean result = false;
		if (array != null && array.length > 0){
			if (s != null && !s.trim().equals("")){
				for (int i = 0; i < array.length; i++){
					if (array[i].equals(s)){ 
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

/*
 * 判断一个长整形n是不是在String[]中
 */	
	public static boolean indexOfArray(String[] array, long n){
		boolean result = false;
		if (array != null && array.length > 0){
			for (int i = 0; i < array.length; i++){
				if (Long.parseLong(array[i]) == n){ 
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
/*
 * 去除字符串中不想要的字符。
 */
	public static String wipe(String arg, String fromString, String toString){
		if (isNotNull(arg)){
			return arg.replace(fromString, toString);
		}else
			return "";
		
	}

/*
 * 将字符串数组转换成list。
 */	
	public static List<String> arrayToList(String[] array){
		List<String> list = null;
		if (array != null && array.length >0){
			list = new ArrayList<String>();
			for (String s : array){
				list.add(s);
			}
		}
		return list;
	}
	
	public static String prettify(String name){
		return "$" + name + "$";
	}
	
}

