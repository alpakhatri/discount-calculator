package com.sg.fnf.strategy;

public interface DiscountStrategy<T>{

	public boolean IsdiscountApplicable(T data);
	
	default public float calculateDiscount(T data){
		return 0;
	}
}
