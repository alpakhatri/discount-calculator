package com.sg.fnf.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sg.fnf.domain.Category;
import com.sg.fnf.domain.InventoryItem;
import com.sg.fnf.service.BrandService;
import com.sg.fnf.service.CategoryService;


public class InventoryServiceTest {
	private static Map<Integer,InventoryItem> itemsById = new HashMap<>();
	private static BrandService brandService = new BrandService();
	private static CategoryService categoryService = new CategoryService();

	@BeforeClass
	public static void setup(){
		String[] inventory = null ;
 		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("/workspace/tesco/discount-calculator/src/main/resources/InventoryDetails"));
			String line = reader.readLine();
			if(line != null){
				inventory = new String[Integer.parseInt(line)];
			}
			if(inventory != null){
				for(int i=0 ; i< inventory.length;i++){
					inventory[i] = reader.readLine();
				}
			}
		} catch (IOException e) {
		}
		finally{
			try {
				if(reader !=null)
					reader.close();
			} catch (IOException e) {
			}
		}
		
		for(int i=0 ; i< inventory.length;i++){
			String[] invItems = inventory[i].split(",");
			itemsById.put(Integer.parseInt(invItems[0]),new InventoryItem(Integer.parseInt(invItems[0]),brandService.getBrandByName(invItems[1]),categoryService.getCategoryByName(invItems[2]),Float.valueOf(invItems[3])));
		}
	}
	
	@Test
	public void testDiscountsApplicable(){
		InventoryItem item = itemsById.get(1);
		List<Float>  discounts = new ArrayList<>();
		discounts.add(item.getBrand().getDiscount());
		discounts.add(item.getCategory().getDiscount());
		List<Category> ancestors = categoryService.getAncestors(item.getCategory());
		ancestors.forEach(category -> discounts.add(category.getDiscount()));
		Float discount = discounts.stream().max(Float::compare).get();
		
		Assert.assertEquals(20.0, discount,0);
	}
	
	@Test
	public void testCalculateDiscount(){
		InventoryItem item = itemsById.get(1);
		float discountToApply = 10;
		float discount = item.getPrice() - ((item.getPrice()*discountToApply)/100);
		Assert.assertEquals(720.0, discount,0);
	}
}

