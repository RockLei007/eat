package com.heracles.eat.service.account;

import java.util.List;

import com.heracles.eat.dao.account.CustomerDao;
import com.heracles.eat.entity.account.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;


/**
 * 顾客管理类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class CustomerManager {

	private CustomerDao customerDao;

	public void save(Customer entity) {
		customerDao.save(entity);
	}

	/**
	 * 使用属性过滤条件查询.
	 */
	@Transactional(readOnly = true)
	public Page<Customer> searchCustomer(final Page<Customer> page, final List<PropertyFilter> filters) {
		return customerDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<Customer> searchCustomer(final Page<Customer> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQL_orgId", String.valueOf(orgId)));
		return customerDao.findPage(page, filters);
	}	
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public Customer getCustomer(Long id){
		return customerDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public Customer getCustomerByWeixinId(Long orgId, String weixinId){
		return customerDao.getCustomerByWeixinId(orgId, weixinId);
	}

	/**
	 * 加入黑名单.
	 */
	public void setBlack(Long id) {
		Customer customer = getCustomer(id);
		customerDao.changeBlack(customer, Customer.BLACK_STATE);
	}

	/**
	 * 取消黑名单.
	 */
	public void cancelBlack(Long id) {
		Customer customer = getCustomer(id);
		customerDao.changeBlack(customer, Customer.WHITE_STATE);
	}
	
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	
}
