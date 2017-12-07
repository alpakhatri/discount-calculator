package com.sg.fnf;

import java.util.Collection;
import java.util.Scanner;

import com.sg.fnf.service.OrderService;

public class OrdersApplication {
	
	private static OrderService service = new OrderService();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int noOfInventoryItems = scanner.nextInt();
		String[] inventory = new String[noOfInventoryItems];
		scanner.nextLine();
		for(int i=0 ; i< noOfInventoryItems;i++){
			inventory[i] = scanner.nextLine();
		}
		scanner.nextLine();
		
		int noOfCustomerOrders = scanner.nextInt();
		String[] orders = new String[noOfCustomerOrders];
		scanner.nextLine();

		for(int i=0 ; i< noOfCustomerOrders;i++){
			orders[i] = scanner.nextLine();
		}
		
		Collection<Float> totalPrices = service.calculateTotalPrice(inventory,orders);

		scanner.nextLine();

		System.out.println("Expected Output:");
		totalPrices.forEach(price -> System.out.println(price));
	}

}


/*
 * 
5
1,Arrow,Shirts,800
2,Vero Moda,Dresses,1400
3,Provogue,Footwear,1800
4,Wrangler,Jeans,2200
5,UCB,Shirts,1500

2
1,2,3,4
1,5

Expected output:
3860
2140

*/
