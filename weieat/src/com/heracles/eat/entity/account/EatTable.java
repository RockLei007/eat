package com.heracles.eat.entity.account;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;


/**
 * 餐桌.
 * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_table")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EatTable extends IdEntity {
	
	public static final int ENABLE_STATE = 0;
	public static final int DISABLE_STATE = 1;

	private List<TableReserve> tableReseveList;
	private List<OrderDishes> orderDishesList;
	
	private Long orgId;
	private String categories;
	private String name;
	private String type;
	private String discription;
	private String identity;
	private int reserve;
	private int islamic;
	private int state;

	public EatTable() {

	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

	public int getIslamic() {
		return islamic;
	}

	public void setIslamic(int islamic) {
		this.islamic = islamic;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="eatTable")  
    @OrderBy(value = "id ASC")
    public List<TableReserve> getTableReserveList(){
    	return tableReseveList;
    }
    
    public void setTableReserveList(List<TableReserve> tableReseveList){
    	this.tableReseveList = tableReseveList;
    }	

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="eatTable")  
    @OrderBy(value = "id ASC")
    public List<OrderDishes> getOrderDishesList(){
    	return orderDishesList;
    }
    
    public void setOrderDishesList(List<OrderDishes> orderDishesList){
    	this.orderDishesList = orderDishesList;
    }	
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	
}
