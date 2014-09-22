package com.heracles.eat.service.account;

import java.util.List;

import com.heracles.eat.dao.account.ReserveAttentionDao;
import com.heracles.eat.entity.account.ReserveAttention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;


/**
 * 接收消息的管理类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ReserveAttentionManager {

	private ReserveAttentionDao reserveAttentionDao;

	public void save(ReserveAttention entity) {
		reserveAttentionDao.save(entity);
	}
	
	public void delete(ReserveAttention entity){
		reserveAttentionDao.delete(entity);
	}

	/**
	 * 使用属性过滤条件查询.
	 */
	@Transactional(readOnly = true)
	public Page<ReserveAttention> searchReserveAttention(final Page<ReserveAttention> page, final List<PropertyFilter> filters) {
		return reserveAttentionDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ReserveAttention> searchReserveAttention(final Page<ReserveAttention> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQL_orgId", String.valueOf(orgId)));
		return reserveAttentionDao.findPage(page, filters);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public ReserveAttention getReserveAttention(Long id){
		return reserveAttentionDao.get(id);
	}

	@Transactional(readOnly = true)
	public List<ReserveAttention> getAttentionByOrgId(Long orgId){
		return reserveAttentionDao.getAttentionByOrgId(orgId);
	}
	
	public void active(Long orgId, Long userId){
		ReserveAttention activeAtt = reserveAttentionDao.getAttentionByActive(orgId);
		if (activeAtt != null){
			reserveAttentionDao.changeState(activeAtt, ReserveAttention.NORMAL_STATE);
		}
		ReserveAttention normalAtt = reserveAttentionDao.getAttentionByOrgIdUserId(orgId, userId);
		if (normalAtt != null){
			reserveAttentionDao.changeState(normalAtt, ReserveAttention.ACTIVE_STATE);
		}
	}
	
	@Autowired
	public void setReserveAttentionDao(ReserveAttentionDao reserveAttentionDao) {
		this.reserveAttentionDao = reserveAttentionDao;
	}
	
	
}
