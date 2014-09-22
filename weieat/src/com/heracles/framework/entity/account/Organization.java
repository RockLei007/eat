package com.heracles.framework.entity.account;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heracles.framework.entity.IdEntity;

/**
 * 组织机构.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 
 * @author yinzj
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "hera_organization")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//json忽略属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "userList"})
public class Organization extends IdEntity {

	public static final int ACTIVE_STATUS = 0;
	public static final int FORBIDDEN_STATUS = 1;
	
	private String name;
	private Long parent;
	private String address;
	private String phone;
	private String fax;
	private int status; 
	private int level;
	private String symbol;
	private String categories;
	private String description;
	private String defined1;
	private String defined2;
	private String defined3;
	private String createDate;
	private String endDate;
	private String identity;
	private String weixin;
	
	private List<User> userList;
	
	//字段非空且唯一, 用于提醒Entity使用者及生成DDL.
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefined1() {
		return defined1;
	}

	public void setDefined1(String defined1) {
		this.defined1 = defined1;
	}

	public String getDefined2() {
		return defined2;
	}

	public void setDefined2(String defined2) {
		this.defined2 = defined2;
	}

	public String getDefined3() {
		return defined3;
	}

	public void setDefined3(String defined3) {
		this.defined3 = defined3;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="organization")  
    @OrderBy(value = "id ASC")
    public List<User> getUserList(){
    	return userList;
    }
    
    public void setUserList(List<User> userList){
    	this.userList = userList;
    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	
}