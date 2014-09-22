package com.heracles.eat.dao.account;

import java.util.List;

import com.heracles.eat.entity.account.EatTable;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 餐桌对象的泛型DAO类.
 * 
 * @author 31307@sohu.com
 * 
 */
@Component
public class EatTableDao extends HibernateDao<EatTable, Long> {

	public void disable(Long id) {
		EatTable table = get(id);
		table.setState(EatTable.DISABLE_STATE);
		save(table);
	}
	
	public void enable(Long id) {
		EatTable table = get(id);
		table.setState(EatTable.ENABLE_STATE);
		save(table);
	}
	
	public List<EatTable> getTableByOrgId(Long orgId){
		String hsql = "from EatTable where orgId = " + orgId + " and state =0";
		return find(hsql);
	}

	public List<EatTable> getTableByReserve(Long orgId, String categories){
		String hsql = "from EatTable where orgId = " + orgId + " and state = 0 and reserve = 0 and categories ='" + categories + "'";
		return find(hsql);
	}
	
	public Long getFoodCount(Long orgId){
		String hql = "select count(*) from EatTable e where e.orgId = " + orgId;
		return countHqlResult(hql);
	}
	
}
