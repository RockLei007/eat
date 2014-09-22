package com.heracles.framework.task.job;

import com.heracles.framework.service.account.TaskScheduleManager;

public class ClearStopScheduleJob extends AbstractJob {

	private TaskScheduleManager scheduleManage;

	public void run() {
		scheduleManage = (TaskScheduleManager)getAppObject("taskScheduleManager");
		//默认清理三天前的数据
		scheduleManage.deleteLosteEffectiveness(3);
		setMessage("清楚过期的定时任务");
	}

	
}
