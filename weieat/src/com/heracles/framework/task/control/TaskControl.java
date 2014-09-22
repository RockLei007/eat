package com.heracles.framework.task.control;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.heracles.framework.tools.Datetime;

@Component
public class TaskControl{
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private SchedulerFactoryBean schedulerBean;
	private Scheduler sched = null;

	public void run(Class<?> jobClass, String cronExpression) throws Exception{
		sched = schedulerBean.getScheduler();
		String jobName = "job" + jobClass.getSimpleName();
		String triggerName = "trigger" + jobClass.getSimpleName();
		if (sched.getTrigger(triggerName, Scheduler.DEFAULT_GROUP) == null){
			JobDetail job = new JobDetail(jobName, Scheduler.DEFAULT_GROUP, jobClass);
	        CronTrigger trigger = new CronTrigger(triggerName, jobName, cronExpression);
	        trigger.setGroup(Scheduler.DEFAULT_GROUP);
	        sched.scheduleJob(job, trigger);
		}
	}
	
	//onDatetime 的格式为“YYYY-MM-DD hh:mm:ss”，例如“2012-06-05 00:02:00”
	public void onTimeRun(Class<?> jobClass, String onDatetime, int intervalInMinutes, int repeatCount) throws Exception{
		onTimeRun(jobClass, Datetime.StringFormat(onDatetime), intervalInMinutes, repeatCount);
	}

	public void onTimeRun(Class<?> jobClass, Long onDatetime, int intervalInMinutes, int repeatCount) throws Exception{
		String jobName = "job" + jobClass.getSimpleName();
		String triggerName = "trigger" + jobClass.getSimpleName();
		
		if (TaskParameter.getTaskExecuteCount(jobName) < repeatCount){
			if (onDatetime != null && System.currentTimeMillis() > onDatetime){
				sched = schedulerBean.getScheduler();
				if (sched.getTrigger(triggerName, Scheduler.DEFAULT_GROUP) == null){
					JobDetail job = new JobDetail(jobName, Scheduler.DEFAULT_GROUP, jobClass);
			        Trigger trigger = TriggerUtils.makeMinutelyTrigger(intervalInMinutes);
			        trigger.setName(triggerName);
			        trigger.setGroup(Scheduler.DEFAULT_GROUP);
			        sched.scheduleJob(job, trigger);
			        TaskParameter.setTaskRepeatCount(jobName, repeatCount);
				}
			}
		}
		if 	(TaskParameter.getTaskExecuteCount(jobName) >= repeatCount){
			stopJob(jobName, triggerName);
		}
	}

	public void stopJob(String jobName, String triggerName){
		sched = schedulerBean.getScheduler();
		try {
			sched.pauseJob(jobName, Scheduler.DEFAULT_GROUP);
			sched.pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);
			sched.deleteJob(jobName, Scheduler.DEFAULT_GROUP);
			sched.unscheduleJob(triggerName, Scheduler.DEFAULT_GROUP);
			sched.interrupt(jobName, Scheduler.DEFAULT_GROUP);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Autowired
	public void setSchedulerBean(SchedulerFactoryBean schedulerBean){
		this.schedulerBean = schedulerBean;
	}

	
}
