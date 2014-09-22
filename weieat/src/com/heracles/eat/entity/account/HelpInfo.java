package com.heracles.eat.entity.account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 接收的微信信息.
 * 
 * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_help_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HelpInfo{

	private	Long orgId;
	private	String content;
	
	public HelpInfo() {
	}

	@Id
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
	
}
