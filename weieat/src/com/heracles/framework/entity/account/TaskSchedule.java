package com.heracles.framework.entity.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;


/**
 * 任务调度信息.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 
 * @author yinzj
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "hera_task_schedule")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TaskSchedule extends IdEntity {

	public static final int WAIT_STATUS = 0;
	public static final int RUNING_STATUS = 1;
	public static final int STOP_STATUS = 2;

	public static final int KEEPON_TYPE = 0;
	public static final int ONCEONLY_TYPE = 1;

    private int taskType;
    private String cron;
    private String onTime;
    private int intervalMinute;
    private int count;
    private int status;
    private String lastTime;
    
    private Task task;

    public int getTaskType() {
    	return taskType;
    }
    
    public void setTaskType(int taskType) {
    	this.taskType = taskType;
    }
    
    public String getOnTime() {
    	return onTime;
    }
    
    public void setOnTime(String onTime) {
    	this.onTime = onTime;
    }
	
    public String getCron() {
    	return cron;
    }
    
    public void setCron(String cron) {
    	this.cron = cron;
    }
    
    public int getIntervalMinute() {
    	return intervalMinute;
    }
    
    public void setIntervalMinute(int intervalMinute) {
    	this.intervalMinute = intervalMinute;
    }

    public int getCount() {
    	return count;
    }
    
    public void setCount(int count) {
    	this.count = count;
    }

    public int getStatus() {
    	return status;
    }
    
    public void setStatus(int status) {
    	this.status = status;
    }

    @Transient
    public String getLastTime() {
    	return lastTime;
    }
    
    public void setLastTime(String lastTime) {
    	this.lastTime = lastTime;
    }

	@ManyToOne(fetch = FetchType.EAGER)  
	@JoinColumn(name = "task_id")
    public Task getTask() {  
        return task;  
    }  
    
    public void setTask(Task task) {  
        this.task = task;  
    }
    
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
    }
    
    
}
