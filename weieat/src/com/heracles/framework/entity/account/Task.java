package com.heracles.framework.entity.account;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;

/**
 * 任务列表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 
 * @author yinzj
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "hera_task")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends IdEntity {

	private String className;
	private String name;
	private String description;
	
	private List<TaskSchedule> scheduleList;

	//字段非空且唯一, 用于提醒Entity使用者及生成DDL.
	@Column(nullable = false, unique = true)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="task")  
    @OrderBy(value = "id ASC")  
    public List<TaskSchedule> getTaskScheduleList() {  
        return scheduleList;  
    }  
	
    public void setTaskScheduleList(List<TaskSchedule> taskScheduleList) {          
        this.scheduleList = taskScheduleList;  
    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}