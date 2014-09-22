package com.heracles.framework.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类及方法执行跟踪.
 * 
 * @author yinzj
 */

public class Track {
	static Logger logger = LoggerFactory.getLogger(Track.class);
	
	public static void getTrack(int level){ 
		level += 1;
		StackTraceElement stack[] = (new Throwable()).getStackTrace(); 
		for (int i = 1; i < level; i++){
			StackTraceElement ste=stack[i]; 
			logger.info(ste.getClassName()+"."+ste.getMethodName()+"   line:"+ste.getLineNumber());
		}
	} 
	
}
