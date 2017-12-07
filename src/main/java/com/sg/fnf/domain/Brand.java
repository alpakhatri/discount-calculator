package com.sg.fnf.domain;

public class Brand {
	private int id;
	private String name;
	private boolean discountPresent;
	private float discount;
	
	public Brand(int id,String name,boolean discountPresent,float discount){
		this.setId(id);
		this.setName(name);
		this.setDiscount(discount);
		this.setDiscountPresent(discountPresent);
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
	
}
