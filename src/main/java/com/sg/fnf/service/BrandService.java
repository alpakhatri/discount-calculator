package com.sg.fnf.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sg.fnf.domain.Brand;
import com.sg.fnf.strategy.DiscountStrategy;

@Service
public class BrandService implements DiscountStrategy<Brand>{
	
	private static List<Brand> brands = new ArrayList<>();

	public BrandService(){
		initializeAllBrands();
	}
	
	private void initializeAllBrands(){
		BufferedReader reader = null ;
		try{
			 reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/BrandDetails.txt")));
			String line;
			while ((line = reader.readLine()) != null) {
                String[] brandDetails = line.split(",");
                brands.add(new Brand(Integer.parseInt(brandDetails[0]),brandDetails[1],Boolean.valueOf(brandDetails[2]),Float.valueOf(brandDetails[3])));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
            if (reader != null) {
                try {
                	reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public Brand getBrandByName(String brandName){
		return brands.stream().filter(brand -> brand.getName().equals(brandName)).findFirst().get();
	}
	
	@Override
	public boolean IsdiscountApplicable(Brand data) {
		return data.isDiscountPresent();
	}
	
	
}
