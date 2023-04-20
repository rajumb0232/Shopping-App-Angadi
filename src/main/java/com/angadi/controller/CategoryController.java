package com.angadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@ApiOperation(value = "Add Category", notes = "API is used to save the category into the database.")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Category Added successfully."),
			@ApiResponse(code = 404, message = "Failed to add Category!")
			
	})
	@PostMapping
	public ResponseEntity<ResponseStructure<Category>> addCategory(@Validated @RequestBody Category category){
		return service.addCategory(category);
	}
	
	@ApiOperation(value = "Get Products by Category", notes = "API is used to fetch all the Products Based on the Category.")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Products found in Category."),
			@ApiResponse(code = 404, message = "Failed to find Products By Category!")
			
	})
	@GetMapping("/categoryProducts")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProductsByCategory(@RequestParam int categoryId){
		return service.getCategoryProducts(categoryId);
	}
	
	@ApiOperation(value = "Get Categoy by PrimeCategory", notes = "API is used to fetch all the Category Based on the PrimeCategory,"
			+ " (PrimeCategory is the root category on which the shops and categories are differenciated with).")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Categories found in PrimeCategory."),
			@ApiResponse(code = 404, message = "Failed to find Categories By PrimeCategory!")
			
	})
	@GetMapping("/byPrimeCategory")
	public ResponseEntity<ResponseStructure<List<CategoryDto>>> getAllCategoriesByPrimeCategory(@RequestParam PrimeCategory primeCategory){
		return service.getAllCategoriesByPrimeCategory(primeCategory);
	}
	
	@ApiOperation(value = "Update Category", notes = "API used to update Category based on the categoryId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Category updated successfully!"),
			@ApiResponse(code = 404, message = "Failed to update Category!")
			
	})
	@PutMapping
	public ResponseEntity<ResponseStructure<CategoryDto>> updateCategory(@Validated @RequestBody Category category, @RequestParam int categoryId){
		return service.updateCategory(category, categoryId);
	}
	
	@ApiOperation(value = "Delete Category", notes = "API used to delete Category based on the categoryId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Category deleted successfully!"),
			@ApiResponse(code = 404, message = "Failed to delete Category!")
			
	})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Category>> deleteCategory(@RequestParam int categoryId){
		return service.deleteCategory(categoryId);
	}
}
