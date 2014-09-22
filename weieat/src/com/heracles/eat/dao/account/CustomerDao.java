package com.heracles.eat.dao.account;

import java.util.List;

import com.heracles.eat.entity.account.Customer;
import com.heracles.framework.tools.Unit;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 菜对象的泛型DAO类.
 * 
 * @author 31307@sohu.com
 */
@Component
public class CustomerDao extends HibernateDao<Customer, Long> {
	
	public void changeBlack(Customer customer, int state){
		customer.setBlack(state);
		save(customer);
	}

	public Customer getCustomerByWeixinId(Long orgId, String weixinId){
		String hql = "from Customer where weixinId = '" + weixinId + "' and orgId = " + orgId + " and black = " + Customer.WHITE_STATE;
		List<Customer> list = find(hql);
		if (Unit.isNotNull(list)){
			return list.get(0);
		}else
			return null;
	}
	
	
}
