package com.heracles.framework.task.control;

import java.util.HashMap;
import java.util.Map;

public class TaskParameter {

	private static Map<String, Object> taskMap = new HashMap<String, Object>();
	private static Long startTime;
	
	public static void setTaskDatetime(String jobName, String datetime){
		taskMap.put(jobName + "DateTime", datetime);
	}
	
	public static String getTaskDatetime(String jobName){
		return taskMap.get(jobName + "DateTime").toString();
	}
	
	public static void clearTaskDatetime(String jobName){
		taskMap.remove(jobName + "DateTime");
	}
	
	public static void setTaskExecuteCount(String jobName){
		taskMap.put(jobName + "count", getTaskExecuteCount(jobName) + 1);
	}
	
	public static int getTaskExecuteCount(String jobName){
		if (taskMap.get(jobName + "count") != null) {
			return (Integer)taskMap.get(jobName + "count");
		}else
			return 0;
	}

	public static void setTaskRepeatCount(String jobName, int repeatCount){
		taskMap.put(jobName + "RepeatCount", repeatCount);
	}
	
	public static int getTaskRepeatCount(String jobName){
		if (taskMap.get(jobName + "RepeatCount") != null) {
			return (Integer)taskMap.get(jobName + "RepeatCount");
		}else
			return 0;
	}	
	
	public static void clearTaskExecuteCount(String jobName){
		taskMap.remove(jobName + "count");
	}
		
	public static void setStartTime(Long _startTime){
		startTime = _startTime;
	}
	
	public static Long getStartTime(){
		return startTime;
	}
	
	
}
