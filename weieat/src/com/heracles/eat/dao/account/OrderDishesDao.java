package com.heracles.eat.dao.account;

import java.util.List;

import com.heracles.eat.entity.account.OrderDishes;
import com.heracles.framework.tools.Unit;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 点菜菜对象的泛型DAO类.
 * 
 * @author 31307@sohu.com
 */
@Component
public class OrderDishesDao extends HibernateDao<OrderDishes, Long> {

	public void change(OrderDishes orderDishes, int state) {
		orderDishes.setState(state);
		save(orderDishes);
	}

	public List<OrderDishes> getAddOrderDishes(Long orgId) {
		String hql = "from OrderDishes where orgId = " + orgId + " and state in (" +  
				OrderDishes.NEW_STATE +","  + OrderDishes.VERIFY_STATE + ","  + OrderDishes.ADD_STATE +") order by datetime DESC";
		return find(hql);
	}
	
	public List<OrderDishes> getNewOrderDishes(Long orgId) {
		String hql = "from OrderDishes where orgId = " + orgId + " and state in (" +  
				OrderDishes.NEW_STATE + "," + OrderDishes.ADD_STATE +") order by datetime DESC";
		return find(hql);
	}
	
	public List<OrderDishes> getVerifyOrderDishes(Long orgId) {
		String hql = "from OrderDishes where orgId = " + orgId + " and state in (" +  
				OrderDishes.VERIFY_STATE + "," + OrderDishes.ADD_STATE +") order by datetime DESC";
		return find(hql);
	}
	
	public OrderDishes getOrderDishesByTableId(Long orgId, Long tableId){
		String hql = "from OrderDishes where orgId = " + orgId + " and tableId = " + tableId + " and state in (" +  
				OrderDishes.NEW_STATE +","  + OrderDishes.VERIFY_STATE + ","  + OrderDishes.ADD_STATE +") order by datetime DESC";
		List<OrderDishes> list = find(hql);
		if (Unit.isNotNull(list)){
			return list.get(0);
		}else
			return null;
	}
	
	public List<OrderDishes> getDishesByTableId(Long orgId, Long tableId, String weixinId){
		String hql = "select o from OrderDishes o join o.eatTable t where o.orgId = " + orgId + " and t.id = " + tableId + " and o.foodArray like '%\"weixinId\":\""+weixinId+"\"%' order by datetime DESC";
		return find(hql);
	}
	
	public Long getNewDishesCount(Long orgId){
		String hql = "select count(*) from OrderDishes o where o.orgId = " + orgId + " and o.state = " + OrderDishes.NEW_STATE;
		return countHqlResult(hql);
	}

	public Long getAddDishesCount(Long orgId){
		String hql = "select count(*) from OrderDishes o where o.orgId = " + orgId + " and o.state = " + OrderDishes.ADD_STATE;
		return countHqlResult(hql);
	}
	
}
