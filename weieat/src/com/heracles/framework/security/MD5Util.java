package com.heracles.framework.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具.
 * 
 * @author yinzj
 */

public class MD5Util {

	public final static String MD5(String s) {
	    try {
	     byte[] btInput = s.getBytes();
	     MessageDigest mdInst = MessageDigest.getInstance("MD5");
	     mdInst.update(btInput);
	     byte[] md = mdInst.digest();
	     StringBuffer sb = new StringBuffer();
	     for (int i = 0; i < md.length; i++) {
	      int val = ((int) md[i]) & 0xff;
	      if (val < 16)
	       sb.append("0");
	      sb.append(Integer.toHexString(val));
	    
	     }
	     return sb.toString();
	    } catch (Exception e) {
	     return null;
	    }
	}
	
	public static String sha1(String value) {
		try {
			return hash(MessageDigest.getInstance("SHA1"), value);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
			return null;
	}
	
	private static String hash(MessageDigest digest,String src) {
		return toHexString(digest.digest(src.getBytes()));
	}

	private static String toHexString(byte[] bytes) {
		char[] values = new char[bytes.length * 2];
		int i=0;
		for(byte b : bytes) {
			values[i++] = LETTERS[((b & 0xF0) >>> 4)];
			values[i++] = LETTERS[b & 0xF];
		}
		return String.valueOf(values);
	}

	private static final char[] LETTERS = "0123456789ABCDEF".toCharArray();
	
	
}
