package com.heracles.eat.entity.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.heracles.framework.entity.IdEntity;


/**
 * 菜.
 * 
  * 
 * @author 31307@sohu.com
 */
@Entity
@Table(name = "eat_food")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Food extends IdEntity {

	public static final int ENABLE_STATE = 0;
	public static final int DISABLE_STATE = 1;
	
	public static final int PORTION_TYPE = 0;
	public static final int WEIGHT_TYPE = 1;
	
	private Long orgId;
	private	String name;
	private	String categories;
	private	Double price;
	private String image; 
	private	String discription;
	private	String special;
	private	String classes;
	private int state;
	private int type;

	public Food() {
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

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
}
