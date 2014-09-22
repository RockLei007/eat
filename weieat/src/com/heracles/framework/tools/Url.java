package com.heracles.framework.tools;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heracles.framework.general.GeneralToken;


public class Url {
	
	final Logger log = LoggerFactory.getLogger(getClass());
	
	public static final int URL_GET = 0;
	public static final int URL_POST = 1;
	InputStream inStrm = null;
	
	public static Url getInstance(){
		return new Url();
	}
	
	public String connection(String urlString, String content, int type){
		
		String result = "";
		if (type == URL_GET) {
			urlString = urlString+"?"+content;
		}
		try {
			URL url = new URL(urlString);
			URLConnection rulConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setRequestProperty("user-agent","mozilla/4.0 (compatible; msie 6.0; windows 2000)");
			httpUrlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Charset", GeneralToken.getEncoder());
			if (type == URL_POST && content != null && !content.trim().equals("")){
				httpUrlConnection.setRequestMethod("POST");
			}
			httpUrlConnection.setConnectTimeout(50000);
			httpUrlConnection.setReadTimeout(50000);  
			httpUrlConnection.connect();	
			
			if (type == URL_POST && content != null && !content.equals("")){
				DataOutputStream out = new DataOutputStream(httpUrlConnection.getOutputStream());  
				//content = URLEncoder.encode(content, "UTF-8"); 
				out.writeBytes(content);
				out.flush();
				out.close();
			}
			inStrm = httpUrlConnection.getInputStream();
			result = toString(inStrm);
			httpUrlConnection.disconnect();
						
		} catch (IOException e) {
			log.error("error connection, "+e.getMessage()+", please check you are URL string!");
		}
		return result;
	}
	
	public InputStream getInputStream(){
		return inStrm;
	}
	
	private String toString(final InputStream inStrm){
		BufferedReader breader;
		StringBuffer sb = new StringBuffer();

		try {
			breader = new BufferedReader(new InputStreamReader(inStrm, GeneralToken.getEncoder()));
			String str = breader.readLine();
			while(str != null){ 
				sb.append(str+'\n');
				str = breader.readLine();
			} 
		} catch (IOException e) {
			log.error(e.getMessage());
		} 
		return sb.toString();
	}

}
