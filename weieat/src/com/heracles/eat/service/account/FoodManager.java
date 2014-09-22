package com.heracles.eat.service.account;

import java.util.List;

import com.heracles.eat.dao.account.FoodDao;
import com.heracles.eat.entity.account.Food;

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
public class FoodManager {

	private FoodDao foodDao;

	public void save(Food entity) {
		foodDao.save(entity);
	}

	/**
	 * 使用属性过滤条件查询.
	 */
	@Transactional(readOnly = true)
	public Page<Food> searchFood(final Page<Food> page, final List<PropertyFilter> filters) {
		return foodDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<Food> searchFood(final Page<Food> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQL_orgId", String.valueOf(orgId)));
		return foodDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<Food> getFoodGroupByCate(Page<Food> page, Long orgId){
		page.setResult(foodDao.getFoodByCate(orgId));
		return page;
	}
	
	@Transactional(readOnly = true)
	public Long getFoodCount(Long orgId){
		return foodDao.getFoodCount(orgId);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public Food getFood(Long id){
		return foodDao.get(id);
	}

	/**
	 * 禁用餐桌.
	 */
	public void disable(Long id) {
		foodDao.disable(id);
	}

	/**
	 * 启用餐桌.
	 */
	public void enable(Long id) {
		foodDao.enable(id);
	}
	
	@Autowired
	public void setFoodDao(FoodDao foodDao) {
		this.foodDao = foodDao;
	}
	
	
}
