package com.heracles.eat.entity.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;


/**
 * 接收的微信信息.
 * 
 * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_receive_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReceiveMessage extends IdEntity {

	private	String datetime;
	private	String content;
	
	public ReceiveMessage() {
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getContent() {
		return content.replace("<", "&lt;").replace(">", "&gt;");
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
	
}
