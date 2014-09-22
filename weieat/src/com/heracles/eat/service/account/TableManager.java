package com.heracles.eat.service.account;

import java.util.List;

import com.heracles.eat.dao.account.EatTableDao;
import com.heracles.eat.entity.account.EatTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;


/**
 * 界面餐桌的管理类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class TableManager {

	private EatTableDao eatTableDao;

	public void save(EatTable entity) {
		eatTableDao.save(entity);
	}

	/**
	 * 使用属性过滤条件查询餐桌.
	 */
	@Transactional(readOnly = true)
	public Page<EatTable> searchEatTable(final Page<EatTable> page, final List<PropertyFilter> filters) {
		return eatTableDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<EatTable> searchEatTable(final Page<EatTable> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQL_orgId", String.valueOf(orgId)));
		return eatTableDao.findPage(page, filters);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public EatTable getEatTable(Long id){
		return eatTableDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public Long getFoodCount(Long orgId){
		return eatTableDao.getFoodCount(orgId);
	}
	
	@Transactional(readOnly = true)
	public List<EatTable> getTableByOrgId(Long orgId){
		return eatTableDao.getTableByOrgId(orgId);
	}

	/**
	 * 禁用餐桌.
	 */
	public void disable(Long id) {
		eatTableDao.disable(id);
	}

	/**
	 * 启用餐桌.
	 */
	public void enable(Long id) {
		eatTableDao.enable(id);
	}
	
	@Autowired
	public void setEatTableDao(EatTableDao eatTableDao) {
		this.eatTableDao = eatTableDao;
	}
	
	
}
