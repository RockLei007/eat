package com.heracles.framework.tools;

import java.io.UnsupportedEncodingException;

/**
 * 字符转换类
 * 
 * @author yinzj
 */

public class Unicode {

	public static String isoToUTF8(String s){
		String t = null;
		if (s != null){
			try {
				t = new String(s.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	public static String gbkToUTF8(String s){
		String t = null;
		if (s != null){
			try {
				t = new String(s.getBytes("GBK"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	public static String gbToUTF8(String s){
		String t = null;
		if (s != null){
			try {
				t = new String(s.getBytes("GB2312"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public static String gbToIso(String s){
		String t = null;
		if (s != null){
			try {
				t = new String(s.getBytes("GB2312"), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	public static String isoToGb(String s){
		String t = null;
		if (s != null){
			try {
				t = new String(s.getBytes("ISO8859-1"), "GB2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return t;
	}

	public static String isoToGbk(String s){
		String t = null;
		if (s != null){
			try {
				t = new String(s.getBytes("ISO8859-1"), "GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public static String gbToGbk(String s){
		String t = null;
		if (s != null){
			try {
				t = new String(s.getBytes("GB2312"), "GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public static String urlUTF8Encoder(String s){
		return Unit.toURLEncoder(s, "UTF-8");
	}
	
}
