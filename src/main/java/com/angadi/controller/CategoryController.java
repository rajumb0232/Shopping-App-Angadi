package com.angadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.dto.CategoryDto;
import com.angadi.entity.Category;
import com.angadi.entity.Product;
import com.angadi.enums.PrimeCategory;
import com.angadi.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Category>> addCategory(@RequestBody Category category){
		return service.addCategory(category);
	}
	
	@GetMapping("/categoryProducts")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProductsByCategory(@RequestParam int id){
		return service.getCategoryProducts(id);
	}
	
	@GetMapping("/byPrimeCategory")
	public ResponseEntity<ResponseStructure<List<CategoryDto>>> getAllCategoriesByPrimeCategory(@RequestParam PrimeCategory primeCategory){
		return service.getAllCategoriesByPrimeCategory(primeCategory);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<CategoryDto>> updateCategory(@RequestBody Category category, @RequestParam int id){
		return service.updateCategory(category, id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Category>> deleteCategory(@RequestParam int id){
		return service.deleteCategory(id);
	}
}
