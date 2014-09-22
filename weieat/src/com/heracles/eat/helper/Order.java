package com.heracles.eat.helper;

public class Order {
	
	private Long foodId;
	private Double number;
	private Double price;
	private String name;
	private String weixinId;
	private Long datetime;
	private String categories;
	
	public Long getFoodId() {
		return foodId;
	}
	
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	
	public Double getNumber() {
		return number;
	}
	
	public void setNumber(Double number) {
		this.number = number;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWeixinId() {
		return weixinId;
	}
	
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	
	public Long getDatetime() {
		return datetime;
	}
	
	public void setDatetime(Long datetime) {
		this.datetime = datetime;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}
	

}
