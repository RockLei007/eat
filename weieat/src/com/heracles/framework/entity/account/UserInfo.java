package com.heracles.framework.entity.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne; 

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;


/**
 * 用户详细信息.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 
 * @author yinzj
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "hera_user_info")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserInfo extends IdEntity {

	private String lastLoginDate;
    private String address;
    private String phone;
    private String mobile;
    private String qq;
    private String msn;
    private int sex;
    private Long credentialsType; 
    private String identity;
    private Long diplomas;
    private Long political;
    
    private User user;

   /* @Id
    @GeneratedValue(generator = "pkGenerator")   
    //将主键与User关联
    @GenericGenerator(   
         name = "pkGenerator",   
         strategy = "foreign",
         parameters = {@Parameter(name ="property", value = "user")})
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/
    
    public String getLastLoginDate() {
    	return lastLoginDate;
    }
    
    public void setLastLoginDate(String lastLoginDate) {
    	this.lastLoginDate = lastLoginDate;
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
    
    public String getMobile() {
    	return mobile;
    }
    
    public void setMobile(String mobile) {
    	this.mobile = mobile;
    }
    
    public String getQq() {
    	return qq;
    }
    
    public void setQq(String qq) {
    	this.qq = qq;
    }
    
    public String getMsn() {
    	return msn;
    }
    
    public void setMsn(String msn) {
    	this.msn = msn;
    }
    public int getSex() {
    	return sex;
    }
    
    public void setSex(int sex) {
    	this.sex = sex;
    }
    
    public Long getCredentialsType() {
    	return credentialsType;
    }
    
    public void setCredentialsType(Long credentialsType) {
    	this.credentialsType = credentialsType;
    }
	
    public String getIdentity() {
    	return identity;
    }
    
    public void setIdentity(String identity) {
    	this.identity = identity;
    }
    
    public Long getDiplomas() {
    	return diplomas;
    }
    
    public void setDiplomas(Long diplomas) {
    	this.diplomas = diplomas;
    }
    
    public Long getPolitical() {
    	return political;
    }
    
    public void setPolitical(Long political) {
    	this.political = political;
    }
    
    @OneToOne(optional  = false, cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "userInfo")
    @PrimaryKeyJoinColumn 
    public User getUser() {  
        return user;  
    }  
    
    public void setUser(User user) {  
        this.user = user;  
    }
    
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
    }
    
    
}
