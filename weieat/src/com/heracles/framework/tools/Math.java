package com.heracles.framework.tools;

public class Math {
	
	//小数取整，不考虑四舍五入
	public static int getIntegerPart(Double d){
		String str = d.toString();
		if (str.indexOf(".") > 0){
			String[] args=str.split(".");
			return Integer.parseInt(args[0]);
		}else
			return d.intValue();
	}

}
