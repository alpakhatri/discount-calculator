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


public class CategoryServiceTest {

	private static Map<Integer,Category> categoriesById = new HashMap<>();
	@BeforeClass
	public static void setup(){
		BufferedReader reader = null ;
		try{
			 reader = new BufferedReader(new FileReader("discount-calculator/src/test/resources/CategoryDetails.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
                String[] categoryDetails = line.split(",");
                categoriesById.put(Integer.parseInt(categoryDetails[0]),new Category(Integer.parseInt(categoryDetails[0]),categoryDetails[1],Integer.parseInt(categoryDetails[2]),Boolean.valueOf(categoryDetails[3]),Float.valueOf(categoryDetails[4])));
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
	public void testAncestors(){
		List<Category> parentCategories = new ArrayList<>();
		Category category  = categoriesById.get(5);
		while(category !=null && category.getParentCategoryId() != -1 ){
			Category parentCategory = getParentCategory(category);
			category = parentCategory;
			parentCategories.add(category);
		}
		Assert.assertEquals(2, parentCategories.size());
	}
	
	public Category getParentCategory(Category category){
		return category.getParentCategoryId() != -1 ? categoriesById.get(category.getParentCategoryId()) : null;
	}
	
	@Test
	public void testParentCategory(){
		Category category  = categoriesById.get(3);
		Category parentCat =  categoriesById.get(category.getParentCategoryId()) ;
		Assert.assertEquals(-1,parentCat.getParentCategoryId());
	}
}

