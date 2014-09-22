package com.heracles.framework.service.account;

import java.util.List;

import com.heracles.framework.dao.account.TaskDao;
import com.heracles.framework.dao.account.TaskScheduleDao;
import com.heracles.framework.entity.account.Task;
import com.heracles.framework.entity.account.TaskSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

/**
 * 定时住处管理类.
 * 
 * @author yinzj
 */

@Component
@Transactional
public class TaskScheduleManager {

	private TaskDao taskDao;
	private TaskScheduleDao taskScheduleDao;

	//-- User Manager --//
	@Transactional(readOnly = true)
	public Task getTask(Long id) {
		return taskDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public TaskSchedule getTaskSchedule(Long id){
		return taskScheduleDao.get(id);
	}

	public void saveTaskSchedule(TaskSchedule entity) {
		taskScheduleDao.save(entity);
	}

	public void delete(Long id){
		taskScheduleDao.delete(id);
	}
	
	public void deleteLosteEffectiveness(int space){
		taskScheduleDao.deleteLosteEffectiveness(space);
	}
	
	@Transactional(readOnly = true)
	public List<TaskSchedule> getKeepOnTask(){
		return taskScheduleDao.getKeepOnTask();
	}
	
	@Transactional(readOnly = true)
	public List<TaskSchedule> getOnceOnlyTask(){
		return taskScheduleDao.getOnceOnlyTask();
	}
	
	public void changeOtherStatusToWait(){
		taskScheduleDao.changeOtherStatusToWait();
	}

	public void changeWaitToRun(TaskSchedule schedule){
		taskScheduleDao.changeWaitToRun(schedule);
	}
	
	public void changeRunToStop(Long taskScheduleId){
		taskScheduleDao.changeRunToStop(taskScheduleId);
	}
	
	public void changeRunToStop(TaskSchedule schedule){
		taskScheduleDao.changeRunToStop(schedule);
	}
	
	@Transactional(readOnly = true)
	public TaskSchedule getRunScheduleByClassName(String className){
			return taskScheduleDao.getRunScheduleByTaskId(className);
	}
	
	/**
	 * 使用属性过滤条件查询用户.
	 */
	@Transactional(readOnly = true)
	public Page<TaskSchedule> searchTaskSchedule(final Page<TaskSchedule> page, final List<PropertyFilter> filters) {
		return taskScheduleDao.findPage(page, filters);
	}

	@Autowired
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	@Autowired
	public void setTaskScheduleDao(TaskScheduleDao taskScheduleDao) {
		this.taskScheduleDao = taskScheduleDao;
	}
	
	
}
