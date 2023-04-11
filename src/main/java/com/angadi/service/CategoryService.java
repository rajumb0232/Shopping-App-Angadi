package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.CategoryNotFoundByIdException;
import com.angadi.Exception.NoProductsInCategoryException;
import com.angadi.Exception.PrimeCategoryNotFoundException;
import com.angadi.dao.CategoryDao;
import com.angadi.dto.CategoryDto;
import com.angadi.entity.Category;
import com.angadi.entity.Product;
import com.angadi.enums.PrimeCategory;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao dao;

	public ResponseEntity<ResponseStructure<Category>> addCategory(Category category) {
		ResponseStructure<Category> structure = new ResponseStructure<>();
		Category category2 = dao.addCategory(category);
		
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Category added successfully.");
			structure.setData(category2);
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getCategoryProducts(int id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = dao.getCategoryProducts(id);
		
		if(products.isEmpty()) {
			throw new NoProductsInCategoryException("Failed to find Products!");
		}else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Successfully found all products by Category.");
			structure.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<CategoryDto>>> getAllCategoriesByPrimeCategory(PrimeCategory category) {
		ResponseStructure<List<CategoryDto>> structure = new ResponseStructure<>();
		List<CategoryDto> categories = dao.getCategoriesByPrimeCategory(category);
		
		if(categories.isEmpty()) {
			throw new PrimeCategoryNotFoundException("Failed to find categories!");
		}else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Successfully found all categories by PrimeCategory.");
			structure.setData(categories);
			return new ResponseEntity<ResponseStructure<List<CategoryDto>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<CategoryDto>> updateCategory(Category category, int id) {
		ResponseStructure<CategoryDto> structure = new ResponseStructure<>();
		CategoryDto category2 = dao.updateCategory(category, id);
		
		if(category2!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Category updated successfully.");
			structure.setData(category2);
			return new ResponseEntity<ResponseStructure<CategoryDto>>(structure, HttpStatus.OK);
		}else {
			throw new CategoryNotFoundByIdException("Failed to update Product!");
		}
	}

	public ResponseEntity<ResponseStructure<Category>> deleteCategory(int id) {
		ResponseStructure<Category> structure = new ResponseStructure<>();
		Category category2 = dao.deleteCategory(id);
		
		if(category2!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Category deleted successfully.");
			structure.setData(category2);
			return new ResponseEntity<ResponseStructure<Category>>(structure, HttpStatus.OK);
		}else {
			throw new CategoryNotFoundByIdException("Failed to delete Category!");
		}
	}
	
	

	
}
