package com.heracles.weixin;

import com.heracles.framework.xml.ReadXMLStream;

public class Translation {
	
	public static final String TEXT_TYPE="text";
	public static final String IMAGE_TYPE="image";
	public static final String LINK_TYPE="link";
	public static final String LOCATION_TYPE= "location";
	public static final String EVENT_TYPE= "event";
	
	private ReadXMLStream stream = ReadXMLStream.getInstance(msgXML);
		
	private Translation(){
	};
	
	private static String msgXML; 
	
	public static Translation getInstance(String _msgXML){
		msgXML = _msgXML;
		return new Translation();
	}

	public String getToUserName(){
		return stream.read("ToUserName").trim();
	}
	
	public String getFromUserName(){
		return stream.read("FromUserName").trim();
	}
	
	public String getMsgType(){
		return stream.read("MsgType").trim();
	}

	public String getContent(){
		return stream.read("Content").trim();
	}
	
	
}
