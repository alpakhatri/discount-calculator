package com.sg.fnf.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sg.fnf.domain.InventoryItem;

public class OrderService {

	private InventoryService invService = new InventoryService();
	
	public Collection<Float> calculateTotalPrice(String[] inv, String[] orders) {
		Map<Integer,Float> totalDiscountedPrice = new HashMap<>();
		
		Map<Integer,InventoryItem> itemsById = invService.generateInventoryFromInput(inv);
		
		Map<Integer,List<InventoryItem>> itemsPerOrder = new HashMap<>();
		for(int j=0;j<orders.length;j++){
			String[] orderInvIds = orders[j].split(",");
			itemsPerOrder.put(j+1, new ArrayList<InventoryItem>());
			for(int i=0; i< orderInvIds.length;i++){
				itemsPerOrder.get(j+1).add(itemsById.get(Integer.parseInt(orderInvIds[i])));
			}
		}
		
		itemsPerOrder.keySet().forEach(order -> {
			List<InventoryItem> items = itemsPerOrder.get(order);
			items.forEach(item -> {
				float calculatedDiscount = invService.calculateDiscount(item);
				item.setDiscountedPrice(calculatedDiscount);
				Float oldVal = totalDiscountedPrice.get(order) != null ? totalDiscountedPrice.get(order) : 0;
				totalDiscountedPrice.put(order, oldVal+calculatedDiscount);
			});
		});
		
		return totalDiscountedPrice.values();
	}
		
}
