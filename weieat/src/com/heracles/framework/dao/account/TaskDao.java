package com.heracles.framework.dao.account;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.heracles.framework.entity.account.Task;
import com.heracles.framework.tools.Unit;


/**
 * 用户对象的泛型DAO类.
 * 
 * @author yinzj
 */
@Component
public class TaskDao extends HibernateDao<Task, Long> {
	
	public Task getTaskByClassName(String className){
		String hql = "from Task where className = '" + className + "'";
		List<Task> list = find(hql);
		if (Unit.isNotNull(list)){
			return list.get(0);
		}else
			return null;
	}

}
