package com.sg.fnf.domain;

public class InventoryItem {
	private int id;
	private Brand brand;
	private Category category;
	private float price;
	private float discountedPrice;
	
	public InventoryItem(int id, Brand brand,Category category, float price ){
		this.setId(id);
		this.setBrand(brand);
		this.setCategory(category);
		this.setPrice(price);
	}
	
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}


	public float getDiscountedPrice() {
		return discountedPrice;
	}


	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	
}
