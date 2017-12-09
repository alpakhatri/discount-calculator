package com.sg.fnf.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sg.fnf.domain.Brand;


public class BrandServiceTest {

	private static List<Brand> brands = new ArrayList<>();
	@BeforeClass
	public static void setup(){
		BufferedReader reader = null ;
		try{
			 reader = new BufferedReader(new FileReader("discount-calculator/src/test/resources/BrandDetails.txt"));
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
	
	@Test
	public void testBrandNameAvailability(){
		String brandName= "Wrangler";
		Brand foundBrand = brands.stream().filter(brand -> brand.getName().equals(brandName)).findFirst().get();
		Assert.assertEquals("Brand Found", brandName, foundBrand.getName());
	}
	
	@Test
	public void testIfDiscountApplicable(){
		String brandName= "Wrangler";
		Brand foundBrand = brands.stream().filter(brand -> brand.getName().equals(brandName)).findFirst().get();
		Assert.assertTrue(foundBrand.isDiscountPresent());
	}
}

