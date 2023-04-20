package com.angadi.controller;

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
import com.angadi.dto.ProductDto;
import com.angadi.entity.Product;
import com.angadi.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@ApiOperation(value = "Add Product", notes = "API used to Save Product to the database "
			+ "(the product will be having the relationship with the Shop and selectedProduct).")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Product Saved successfully!"),
			@ApiResponse(code = 404, message = "Failed to save Product!")
			
	})
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> addProduct(@Validated @RequestBody ProductDto productDto, @RequestParam int shopId, @RequestParam int categoryId){
		return service.addProduct(productDto, shopId, categoryId);
	}
	
	@ApiOperation(value = "Get Product by productId", notes = "API used to fetch Product based on the ProductId.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Product Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Product!")
			
	})
	@GetMapping("/byId")
	public ResponseEntity<ResponseStructure<Product>> getProductById(@RequestParam long productId){
		return service.getProductById(productId);
	}
	
	@ApiOperation(value = "Get Product by productName", notes = "API used to fetch Product based on the ProductName.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Product Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Product!")
			
	})
	@GetMapping("/byName")
	public ResponseEntity<ResponseStructure<Product>> getProductByName(@RequestParam String productName){
		return null;
		// create a method in repo to find by name,
	}
	
	@ApiOperation(value = "Get Product by relatedProductName", notes = "API used to fetch Product based on the relatedProductName.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Product Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Product!")
			
	})
	@GetMapping("/byRelatedName")
	public ResponseEntity<ResponseStructure<Product>> getProductByRelatedName(@RequestParam String name){
		return null;
		// create a method in repo to find by related name, have to create custom query
	}
	
	@ApiOperation(value = "Update Product", notes = "API used to Update Product data based on the ProductId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Product updated Successfully."),
			@ApiResponse(code = 404, message = "Failed to update Product!")
			
	})
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@Validated @RequestBody ProductDto productDto, @RequestParam long productId){
		return service.updateProduct(productDto,productId);
	}
	
	@ApiOperation(value = "Delete Product", notes = "API used to Delete Product based on the ProductId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Product Deleted successfully."),
			@ApiResponse(code = 404, message = "Failed to Delete Product!")
			
	})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@RequestParam long productId){
		return service.deleteProduct(productId);
	}
}
