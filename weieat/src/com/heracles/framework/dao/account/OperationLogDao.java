package com.heracles.framework.dao.account;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.heracles.framework.entity.account.OperationLog;
import com.heracles.framework.tools.Datetime;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author yinzj
 */
@Component
public class OperationLogDao extends HibernateDao<OperationLog, Long> {

	public void deleteLosteEffectiveness(int space){
		String hql = "delete from OperationLog operationLog where operationLog.datetime < '"+ Datetime.getPassBy(0, space, 0) +"'";
		Query q = getSession().createQuery(hql) ; 
		q.executeUpdate();
	}
	
}
