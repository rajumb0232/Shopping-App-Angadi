package com.angadi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.Exception.ProductAlsoBelongToShopException;
import com.angadi.dto.CategoryDto;
import com.angadi.entity.Category;
import com.angadi.entity.Product;
import com.angadi.enums.PrimeCategory;
import com.angadi.repository.CategoryRepo;

@Repository
public class CategoryDao {
	
	@Autowired
	private CategoryRepo repo;
	
	
	public Category addCategory(Category category) {
		return repo.save(category);
	}
	
	// to get all the products present in a particular Category
	public List<Product> getCategoryProducts(int id) {
		Optional<Category> optional = repo.findById(id);
		List<Product> products = new ArrayList<>();
		if(optional.isEmpty()) {
			return null;
		}else {
			products = optional.get().getProducts();
			return products;
		}
	}
	
	// to get all the Categories Present in a single PrimeCategory.
	public List<CategoryDto> getCategoriesByPrimeCategory(PrimeCategory category){
		Optional<List<Category>> optional = repo.findAllByPrimeCategory(category);
		
		if(optional.isEmpty()) {
			return null;
		}else {
			List<CategoryDto> dtos = new ArrayList<>();
			for(Category category2 : optional.get()) {
				CategoryDto dto = new CategoryDto();
				dto.setCategoryId(category2.getCategoryId());
				dto.setCategoryName(category2.getCategoryName());
				dto.setPrimeCategory(category2.getPrimeCategory());
				dtos.add(dto);
			}
		return dtos;
		}
	}
	
	public CategoryDto updateCategory(Category category, int id) {
		Optional<Category> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			category.setCategoryId(id);
			category.setProducts(optional.get().getProducts());
			Category category2 = repo.save(category);
			
			CategoryDto dto = new CategoryDto();
			dto.setCategoryId(category2.getCategoryId());
			dto.setCategoryName(category2.getCategoryName());
			dto.setPrimeCategory(category2.getPrimeCategory());
			
			return dto;
		}
	}
	
	public Category deleteCategory(int id) {
		Optional<Category> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			Category category = optional.get();
			if(category.getProducts().isEmpty()) {
				// should not delete the category if the products are present in it.
				// should have to delete products from the shop then delete category.
				// the category created by one merchant can be used by others also, 
				// therefore giving the authority to a person to delete a category
				// can lead to deletion of the products that other person owns.
				throw new ProductAlsoBelongToShopException("Failed to delete Category! Products also belong to Shop!");
			}
			repo.delete(category);
			CategoryDto dto = new CategoryDto();
			dto.setCategoryId(category.getCategoryId());
			dto.setCategoryName(category.getCategoryName());
			dto.setPrimeCategory(category.getPrimeCategory());
			return category;
		}

	}
}
