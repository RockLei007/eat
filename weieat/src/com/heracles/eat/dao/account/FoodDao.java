package com.heracles.eat.dao.account;

import java.util.List;

import com.heracles.eat.entity.account.Food;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 菜对象的泛型DAO类.
 * 
 * @author 31307@sohu.com
 */
@Component
public class FoodDao extends HibernateDao<Food, Long> {

	public void disable(Long id) {
		Food food = get(id);
		food.setState(Food.DISABLE_STATE);
		save(food);
	}
	
	public void enable(Long id) {
		Food food = get(id);
		food.setState(Food.ENABLE_STATE);
		save(food);
	}
	
	public List<Food> getFoodByCate(Long orgId){
		String hql = "from Food where orgId = " + orgId + " and state = 0 order by categories";
		return find(hql);
	}
	
	public Long getFoodCount(Long orgId){
		String hql = "select count(*) from Food f where f.orgId = " + orgId;
		return countHqlResult(hql);
	}
	
}
