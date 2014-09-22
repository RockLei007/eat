package com.heracles.framework.dao.account;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.heracles.framework.entity.account.TaskSchedule;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author yinzj
 */
@Component
public class TaskScheduleDao extends HibernateDao<TaskSchedule, Long> {

	public void deleteLosteEffectiveness(int space){
		String hql = "delete from TaskSchedule schedule where schedule.onTime <> '' and schedule.onTime < '"+ Datetime.getPassBy(0, 0, space) +"' and schedule.status = " + TaskSchedule.STOP_STATUS;
		Query q = getSession().createQuery(hql) ; 
		q.executeUpdate();
	}
	
	public List<TaskSchedule> getKeepOnTask(){
		String hql = "from TaskSchedule where taskType = " + TaskSchedule.KEEPON_TYPE + " and status = " + TaskSchedule.WAIT_STATUS;
		return find(hql);
	}
	
	public void changeOtherStatusToWait(){
		String hql = "update TaskSchedule set status = " + TaskSchedule.WAIT_STATUS + " where status <> " + TaskSchedule.STOP_STATUS;
		Query q = getSession().createQuery(hql) ; 
		q.executeUpdate();
	}
	
	public List<TaskSchedule> getOnceOnlyTask(){
		String hql = "from TaskSchedule where taskType = " + TaskSchedule.ONCEONLY_TYPE + " and status <> " + TaskSchedule.STOP_STATUS;
		return find(hql);
	}
	
	public void changeWaitToRun(TaskSchedule schedule){
		schedule.setStatus(TaskSchedule.RUNING_STATUS);
		save(schedule);
	}
	
	public void changeRunToStop(Long taskScheduleId){
		String hql = "update TaskSchedule set status = " + TaskSchedule.STOP_STATUS + " where id = " + taskScheduleId;
		Query q = getSession().createQuery(hql) ; 
		q.executeUpdate();
	}
	
	public void changeRunToStop(TaskSchedule schedule){
		schedule.setStatus(TaskSchedule.STOP_STATUS);
		save(schedule);
	}
	
	public TaskSchedule getRunScheduleByTaskId(String className){
		String hql = "from TaskSchedule s inner join fetch s.task t where t.className= '" + className + "' and s.status = " + TaskSchedule.RUNING_STATUS;
		List<TaskSchedule> list = find(hql);
		if (Unit.isNotNull(list)){
			return list.get(0);
		}else
			return null;
	}
	
}
