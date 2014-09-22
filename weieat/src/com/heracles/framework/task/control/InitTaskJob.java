package com.heracles.framework.task.control;

/*
 *将数据库里所有正在执行的任务状态置为初始化状态 
 */
import com.heracles.framework.service.account.TaskScheduleManager;
import com.heracles.framework.task.job.AbstractJob;

public class InitTaskJob extends AbstractJob{

	private static TaskScheduleManager scheduleManage;
	
	public void run() {
		scheduleManage = (TaskScheduleManager)getAppObject("taskScheduleManager");
		scheduleManage.changeOtherStatusToWait();
		TaskParameter.clearTaskDatetime("job"+getClass().getSimpleName());
		setMessage("initialize task schedule is run");
	}


}
