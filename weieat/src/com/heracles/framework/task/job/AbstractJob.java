package com.heracles.framework.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.heracles.framework.general.DispatchServices;
import com.heracles.framework.task.control.TaskParameter;
import com.heracles.framework.tools.Datetime;

public abstract class AbstractJob implements Job{

	protected Logger log = LoggerFactory.getLogger(getClass());
	protected String message;
	protected ApplicationContext springApplicationContext;

	public abstract void run();

	protected Object getAppObject(String appName){
		return springApplicationContext.getBean(appName);
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String jobName = context.getJobDetail().getName();	
		springApplicationContext = DispatchServices.getWebAppContext();
		TaskParameter.setTaskExecuteCount(jobName);
		TaskParameter.setTaskDatetime(jobName, Datetime.getNow());
			
			/*if (GeneralParameter.getTaskRepeatCount(jobName) > 0 && 
			 	GeneralParameter.getTaskExecuteCount(jobName) == GeneralParameter.getTaskRepeatCount(jobName)){
				try {
					context.getScheduler().pauseJob(jobName, Scheduler.DEFAULT_GROUP);
					context.getScheduler().pauseTrigger(context.getTrigger().getName(), Scheduler.DEFAULT_GROUP);
					context.getScheduler().deleteJob(jobName, Scheduler.DEFAULT_GROUP);
				} catch (SchedulerException e) {
					log.error(e.getMessage());
				}
			}else{*/
				run();
				log.info(this.message + ". The " + context.getJobDetail().getName() +" is execute " + TaskParameter.getTaskExecuteCount(jobName) + "!");
			//}
	}
	
	public void setMessage(String message){
		this.message = message;
	}

	
}
