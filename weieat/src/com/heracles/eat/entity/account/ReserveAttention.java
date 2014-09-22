package com.heracles.eat.entity.account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.DoubleIdEntity;



/**
 * 信息通知.
 * 
 * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_reserve_attention")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReserveAttention extends DoubleIdEntity {

	private static final long serialVersionUID = 7266272470820064508L;
	
	public static final int ACTIVE_STATE = 1;
	public static final int NORMAL_STATE = 0;

	public ReserveAttention() {
	}

	private Long orgId;
	private Long userId;
	private String userName;
	private int state;

	@Id
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Id
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
}
