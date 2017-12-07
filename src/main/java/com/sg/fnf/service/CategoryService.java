package com.sg.fnf.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sg.fnf.domain.Category;
import com.sg.fnf.strategy.DiscountStrategy;

public class CategoryService implements DiscountStrategy<Category>{

	private static Map<Integer,Category> categoriesById = new HashMap<>();

	public CategoryService(){
		this.initializeCategories();
	}
	private void initializeCategories(){
		BufferedReader reader = null ;
		try{
			 reader = new BufferedReader(new FileReader("/workspace/tesco/discount-calculator/src/main/resources/CategoryDetails"));
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
	
	public List<Category> getAncestors(Category category){
		List<Category> parentCategories = new ArrayList<>();
		
		while(category !=null && category.getParentCategoryId() != -1 ){
			Category parentCategory = getParentCategory(category);
			category = parentCategory;
			parentCategories.add(category);
		}
		return parentCategories;
	}
	
	public Category getParentCategory(Category category){
		return category.getParentCategoryId() != -1 ? categoriesById.get(category.getParentCategoryId()) : null;
	}
	
	public Category getCategoryByName(String name){
		return categoriesById.values().stream().filter(cateogry -> cateogry.getName().equals(name)).findFirst().get();
	}
	
	@Override
	public boolean IsdiscountApplicable(Category data) {
		return data.isDiscountPresent();
	}
	

}
