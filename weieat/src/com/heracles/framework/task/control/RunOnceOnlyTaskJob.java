package com.heracles.framework.task.control;

import java.util.List;

import com.heracles.framework.entity.account.Task;
import com.heracles.framework.entity.account.TaskSchedule;
import com.heracles.framework.service.account.TaskScheduleManager;
import com.heracles.framework.task.job.AbstractJob;
import com.heracles.framework.tools.Unit;

public class RunOnceOnlyTaskJob extends AbstractJob{

	private static TaskScheduleControl control;
	private static TaskScheduleManager scheduleManage;
	
	public void run() {
		control = (TaskScheduleControl)getAppObject("taskScheduleControl");
		scheduleManage = (TaskScheduleManager)getAppObject("taskScheduleManager");
		List<TaskSchedule> onceOnlyList = scheduleManage.getOnceOnlyTask();
		if (Unit.isNotNull(onceOnlyList)){
			for(TaskSchedule taskSchedule : onceOnlyList){
				runOnceOnlyTask(taskSchedule);
			}
		}
		setMessage("timed scan schedule task");
	}

	private void runOnceOnlyTask(TaskSchedule taskSchedule){
		Task task = taskSchedule.getTask();
		if (Unit.isNotNull(task.getClassName()) && Unit.isNotNull(taskSchedule.getOnTime())){
			try {
				control.onTimeRun(Class.forName(task.getClassName()), taskSchedule.getId(), taskSchedule.getOnTime(), taskSchedule.getIntervalMinute(), taskSchedule.getCount());
				scheduleManage.changeWaitToRun(taskSchedule);
			} catch (ClassNotFoundException e) {
				log.info(e.getMessage());
			} catch (Exception e) {
				log.info(e.getMessage());
			}
			setMessage(task.getName() +". once only task schedule is run");
		}
	}

	
}
