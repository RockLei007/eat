package com.heracles.eat.entity.account;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.eat.helper.Order;
import com.heracles.framework.entity.IdEntity;


/**
 * 点菜.
 * 
 * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_order_dishes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderDishes extends IdEntity {

	public static final int NEW_STATE = 0;
	public static final int VERIFY_STATE = 1;
	public static final int CANCEL_STATE = 2;
	public static final int ADD_STATE = 3;
	public static final int FINISH_STATE = 4;
	
	public static final int NOW_TYPE = 0;
	public static final int ORDER_TYPE = 1;

	private Long orgId;
	private	String foodArray;
	private	Double money;
	private	String datetime;
	private	int	state;
	private int type;
	private Long reserveId;
	private List<Order> foodList;
	
	private EatTable eatTable;
	
	public OrderDishes() {
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public String getFoodArray() {
		return foodArray;
	}

	public void setFoodArray(String foodArray) {
		this.foodArray = foodArray;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getReserveId() {
		return reserveId;
	}

	public void setReserveId(Long reserveId) {
		this.reserveId = reserveId;
	}
	
	public void setFoodList(List<Order> foodList){
		this.foodList = foodList;
	}
	
	@Transient
	public List<?> getFoodList(){
		return foodList;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "table_id")  
    public EatTable getEatTable(){
    	return eatTable;
    }

	public void setEatTable(EatTable table) {
		this.eatTable = table;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
}
