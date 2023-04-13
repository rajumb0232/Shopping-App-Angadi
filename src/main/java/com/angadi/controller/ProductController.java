package com.angadi.controller;

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
import com.angadi.dto.ProductDto;
import com.angadi.entity.Product;
import com.angadi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody ProductDto productDto, @RequestParam int shopId, @RequestParam int categoryId){
		return service.addProduct(productDto, shopId, categoryId);
	}
	
	@GetMapping("/byId")
	public ResponseEntity<ResponseStructure<Product>> getProductById(@RequestParam long id){
		return service.getProductById(id);
	}
	
	@GetMapping("/byName")
	public ResponseEntity<ResponseStructure<Product>> getProductByName(@RequestParam String name){
		return null;
		// create a method in repo to find by name,
	}
	
	@GetMapping("/byRelatedName")
	public ResponseEntity<ResponseStructure<Product>> getProductByRelatedName(@RequestParam String name){
		return null;
		// create a method in repo to find by related name, have to create custom query
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody ProductDto productDto, @RequestParam long id){
		return service.updateProduct(productDto,id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@RequestParam long id){
		return service.deleteProduct(id);
	}
}
