package com.sg.fnf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sg.fnf.domain.Category;
import com.sg.fnf.domain.InventoryItem;

public class InventoryService {
	private BrandService brandService = new BrandService();

	private CategoryService categoryService = new CategoryService();
	
	public Map<Integer,InventoryItem> generateInventoryFromInput(String[] inventory) {
		
		Map<Integer,InventoryItem> itemsById = new HashMap<>();

		for(int i=0 ; i< inventory.length;i++){
			String[] invItems = inventory[i].split(",");
			itemsById.put(Integer.parseInt(invItems[0]),new InventoryItem(Integer.parseInt(invItems[0]),brandService.getBrandByName(invItems[1]),categoryService.getCategoryByName(invItems[2]),Float.valueOf(invItems[3])));
		}
		
		return itemsById;
	}
	
	public float getDiscountApplicable(InventoryItem item){
		List<Float>  discounts = new ArrayList<>();
		discounts.add(item.getBrand().getDiscount());
		discounts.add(item.getCategory().getDiscount());
		List<Category> ancestors = categoryService.getAncestors(item.getCategory());
		ancestors.forEach(category -> discounts.add(category.getDiscount()));
		return discounts.stream().max(Float::compare).get();
	}
	
	
	public float calculateDiscount(InventoryItem item){
		float discountToApply = this.getDiscountApplicable(item);
		return (item.getPrice() - ((item.getPrice()*discountToApply)/100));
	}
}
