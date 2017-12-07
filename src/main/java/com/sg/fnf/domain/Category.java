package com.sg.fnf.domain;

public class Category {
	private int id;
	private String name;
	private int parentCategoryId;
	private boolean discountPresent;
	private float discount;
	
	public Category(int id, String name, int parentCategoryId,boolean discountPresent, float discount){
		this.setId(id);
		this.setName(name);
		this.setParentCategoryId(parentCategoryId);
		this.setDiscountPresent(discountPresent);
		this.setDiscount(discount);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDiscountPresent() {
		return discountPresent;
	}
	public void setDiscountPresent(boolean discountPresent) {
		this.discountPresent = discountPresent;
	}
	public float getDiscount() {
		return this.isDiscountPresent() ? this.discount : 0;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	
}
