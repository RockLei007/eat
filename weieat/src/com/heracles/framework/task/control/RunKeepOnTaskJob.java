package com.heracles.framework.task.control;

import java.util.List;

import com.heracles.framework.entity.account.Task;
import com.heracles.framework.entity.account.TaskSchedule;
import com.heracles.framework.service.account.TaskScheduleManager;
import com.heracles.framework.task.job.AbstractJob;
import com.heracles.framework.tools.Unit;

public class RunKeepOnTaskJob extends AbstractJob{
	
	public void run() {
		TaskScheduleManager scheduleManage = (TaskScheduleManager)getAppObject("taskScheduleManager");
		TaskScheduleControl control = (TaskScheduleControl)getAppObject("taskScheduleControl");
		List<TaskSchedule> keepOnList = scheduleManage.getKeepOnTask();
		if (Unit.isNotNull(keepOnList)){
			for(TaskSchedule keepOnSchedule : keepOnList){
				Task task = keepOnSchedule.getTask();
				if (Unit.isNotNull(task.getClassName()) && Unit.isNotNull(keepOnSchedule.getCron())){
					try {
						control.run(Class.forName(task.getClassName()) , keepOnSchedule.getCron());
						scheduleManage.changeWaitToRun(keepOnSchedule);
					} catch (ClassNotFoundException e) {
						log.info(e.getMessage());
					} catch (Exception e) {
						log.info(e.getMessage());
					}
					setMessage(task.getName() +" task schedule is auto run");
				}
			}
		}
	}
	

}
