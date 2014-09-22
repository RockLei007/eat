package com.heracles.eat.entity.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;

/**
 * 客户管理.
 * 
 * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer extends IdEntity {

	public static final int WHITE_STATE = 0;
	public static final int BLACK_STATE = 1;
	
	private static final long serialVersionUID = 3859838653141043119L;

	public Customer() {
	}
	
	private String weixinId;
	private Long orgId;
	private String name;
	private String phone;
	private String loginName;
	private String createDate;
	private int black;
	
	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getBlack() {
		return black;
	}

	public void setBlack(int black) {
		this.black = black;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
