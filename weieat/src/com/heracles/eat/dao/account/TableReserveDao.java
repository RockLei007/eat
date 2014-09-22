package com.heracles.eat.dao.account;

import java.util.List;

import com.heracles.eat.entity.account.TableReserve;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 点菜菜对象的泛型DAO类.
 * 
 * @author 31307@sohu.com
 */
@Component
public class TableReserveDao extends HibernateDao<TableReserve, Long> {
	
	public void change(TableReserve reserve, int state) {
		reserve.setState(state);
		save(reserve);
	}
	
	public boolean isFrist(Long orgId, Long userId){
		List<TableReserve> list = getTableReserve(orgId, userId);
		if (list != null && list.size() >0){
			return false;
		}else
			return true;
	}
	
	public List<TableReserve> getReserveTable(Long orgId, String categories, String beginTime, String endTime){
		String hql = "select r from TableReserve r join r.eatTable t where r.orgId = " + orgId 
				+ " and t.categories = '" + categories + "' and r.state = " + TableReserve.NEW_STATE
				+ " and (r.beginTime <= '" + beginTime + "' and r.endTime >= '"+ beginTime +"')"
				+ " or (r.beginTime <= '" + endTime + "' and r.endTime >= '" + endTime + "')"
				+ " or (r.beginTime >= '" + beginTime + "' and r.endTime <= '" + endTime + "')";
		return find(hql);
	}
	
	public List<TableReserve> getTableReserve(Long orgId, Long userId){
		String hql = "from TableReserve where orgId = " + orgId + " and userId = " + userId + " order by datetime DESC";
		return find(hql);
	}
	
	public long getNewReserveCount(Long orgId){
		String hql = "select count(*) from TableReserve r where r.orgId = " + orgId + " and r.state = " + TableReserve.NEW_STATE;
		return countHqlResult(hql);
	}
	
}
