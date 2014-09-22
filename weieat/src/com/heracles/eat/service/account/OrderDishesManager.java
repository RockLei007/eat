package com.heracles.eat.service.account;

import java.util.List;

import com.heracles.eat.dao.account.OrderDishesDao;
import com.heracles.eat.entity.account.OrderDishes;

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
public class OrderDishesManager {

	private OrderDishesDao orderDishesDao;

	public void save(OrderDishes entity) {
		orderDishesDao.save(entity);
	}
	
	public void delete(Long id){
		orderDishesDao.delete(id);
	}

	/**
	 * 使用属性过滤条件查询.
	 */
	@Transactional(readOnly = true)
	public Page<OrderDishes> searchOrderDishes(final Page<OrderDishes> page, final List<PropertyFilter> filters) {
		return orderDishesDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<OrderDishes> searchOrderDishes(final Page<OrderDishes> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQL_orgId", String.valueOf(orgId)));
		return orderDishesDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public OrderDishes getOrderDishesByTableId(Long orgId, Long tableId){
		return orderDishesDao.getOrderDishesByTableId(orgId, tableId);
	}
	
	@Transactional(readOnly = true)
	public List<OrderDishes> getDishesByTableId(Long orgId, Long tableId, String weixinId){
		return orderDishesDao.getDishesByTableId(orgId, tableId, weixinId);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public OrderDishes getOrderDishes(Long id){
		return orderDishesDao.get(id);
	}

	public void change(OrderDishes orderDishes, int state) {
		orderDishesDao.change(orderDishes, state);
	}
	
	@Transactional(readOnly = true)
	public Long getNewDishesCount(Long orgId){
		return orderDishesDao.getNewDishesCount(orgId);
	}
	
	@Transactional(readOnly = true)
	public Long getAddDishesCount(Long orgId){
		return orderDishesDao.getAddDishesCount(orgId);
	}
	
	@Autowired
	public void setOrderDishesDao(OrderDishesDao orderDishesDao) {
		this.orderDishesDao = orderDishesDao;
	}

	
}
