package com.heracles.eat.entity.account;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;


/**
 * 预定餐桌.
 * 
 * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_table_reserve")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TableReserve extends IdEntity {

	private static final long serialVersionUID = -489843654780788576L;

	public static final int NEW_STATE = 0;
	public static final int VERIFY_STATE = 1;
	public static final int CANCEL_STATE = 2;
	public static final int FINISH_STATE = 3;
	
	private EatTable eatTable;
	
	private int amount;
	private String datetime;
	private String beginTime;
	private String endTime;
	private Long userId;
	private Long orgId;
	private int state;
	private int first;

	public TableReserve() {
		
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
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
