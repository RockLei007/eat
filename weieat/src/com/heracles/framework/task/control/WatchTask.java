package com.heracles.framework.task.control;

/*
 *守护进程
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.heracles.framework.general.GeneralToken;
import com.heracles.framework.tools.Datetime;

public class WatchTask {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TaskScheduleControl control;
	
	public void executeTask(){
		try {
			control.onTimeRun(InitTaskJob.class, getStartTime(1), 1, 1);
			if (GeneralToken.getIsAutoRunTask()){
				control.onTimeRun(RunKeepOnTaskJob.class, getStartTime(2), 1, 1);
			}
			if (GeneralToken.getIsTimedScanTask()){
				control.run(RunOnceOnlyTaskJob.class, "0 0/3 * * * ?");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Autowired
	public void setTaskScheduleControl(TaskScheduleControl control){
		this.control = control;
	}
	
	private Long getStartTime(int spaceMinute){
		return Datetime.getFutureTime(TaskParameter.getStartTime(), 0, spaceMinute, 0);
	}
	
	
}
