package com.sg.fnf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sg.fnf.service.OrderService;

@SpringBootApplication
@Configuration
@ComponentScan
public class OrdersApplication implements CommandLineRunner  {
    private static final Logger logger = LoggerFactory.getLogger(OrdersApplication.class);

    @Autowired
    private OrderService service;

	public static void main(String[] args) {
		 	SpringApplicationBuilder builder = new SpringApplicationBuilder(OrdersApplication.class).web(false);
	        SpringApplication app = builder.application();
	        app.run(args);
	}

	 @Override
	    public void run(String... args) {
	        Collection<Float> totalPrices = service.calculateTotalPrice(populateInventoryDetails(),populateCustomerOrders());
			writeOutputToFile(totalPrices);
	    }
	 
	 	private void writeOutputToFile(Collection<Float> totalPrices) {
	 		BufferedWriter writer = null;
	 		try {
				 writer = new BufferedWriter(new FileWriter("/workspace/tesco/discount-calculator/src/main/resources/output.txt",true));
				writer.write("Expected Output:\n");
				for(Float price : totalPrices){
						writer.write(String.valueOf(price)+"\n");
				}
			} catch (IOException e) {
				logger.error("There is an error while writing to the file");
			}
	 		finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					logger.error("Error while closing the stream",e);
				}
	 		}
	 		

	 	}

		private String[] populateInventoryDetails() {
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
				logger.error("Error while parsing Inventory Details {}",e);
			}
			finally{
				try {
					if(reader !=null)
						reader.close();
				} catch (IOException e) {
					logger.error("Error while closing the stream",e);
				}
			}
			return inventory;
	 	}
	 	
	 	private String[] populateCustomerOrders() {
			String[] orders = null;
	 		BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader("/workspace/tesco/discount-calculator/src/main/resources/CustomerOrders"));
				String line = reader.readLine();
				if(line != null){
					orders = new String[Integer.parseInt(line)];
				}
				if(orders !=null){
					for(int i=0 ; i< orders.length;i++){
						orders[i] = reader.readLine();
					}
				}
			} catch (IOException e) {
				logger.error("Error while parsing Inventory Details {}",e);
			}
			finally{
				try {
					if(reader !=null)
						reader.close();
				} catch (IOException e) {
					logger.error("Error while closing the stream",e);
				}
			}
			return orders;
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
