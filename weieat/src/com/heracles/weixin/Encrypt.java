package com.heracles.weixin;

import com.heracles.framework.security.MD5Util;
import com.heracles.framework.tools.Unit;

public class Encrypt {

	public static String make(Long orgId, String identity){
		String s = "";
		if (orgId != null && Unit.isNotNull(identity)){
			s = String.valueOf(orgId) + identity.trim();
		}
		return MD5Util.MD5(s);
	}
	
	public static boolean equals(Long orgId, String identity, String key){
		String s = make(orgId, identity);
		return s.equals(key);
	}
	
	
}
