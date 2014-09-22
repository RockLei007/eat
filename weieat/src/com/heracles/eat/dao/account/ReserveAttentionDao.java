package com.heracles.eat.dao.account;

import java.util.List;

import com.heracles.eat.entity.account.ReserveAttention;
import com.heracles.framework.tools.Unit;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 消息通知对象的泛型DAO类.
 * 
 * @author 31307@sohu.com
 */
@Component
public class ReserveAttentionDao extends HibernateDao<ReserveAttention, Long> {
	
	public ReserveAttention getAttentionByActive(Long orgId){
		String hsql = "from ReserveAttention where orgId = " + orgId + " and state = 1";
		List<ReserveAttention> attentionList = find(hsql);
		if (Unit.isNotNull(attentionList)){
			return attentionList.get(0);
		}else{
			return null;
		}
	}
	
	public List<ReserveAttention> getAttentionByOrgId(Long orgId){
		String hsql = "from ReserveAttention where orgId = " + orgId;
		return find(hsql);
	}
	
	public ReserveAttention getAttentionByOrgIdUserId(Long orgId, Long userId){
		String hsql = "from ReserveAttention where orgId = " + orgId + " and userId = "+userId;
		List<ReserveAttention> attentionList = find(hsql);
		if (Unit.isNotNull(attentionList)){
			return attentionList.get(0);
		}else{
			return null;
		}
	}
	
	public void changeState(ReserveAttention attention, int state){
		attention.setState(state);
		save(attention);
	}
	
	
}
