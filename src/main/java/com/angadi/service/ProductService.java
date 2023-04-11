package com.angadi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.ProductNotFoundByIdException;
import com.angadi.dao.ProductDao;
import com.angadi.dto.ProductDto;
import com.angadi.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;

	public ResponseEntity<ResponseStructure<Product>> addProduct(ProductDto productDto) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product2 = dao.addProduct(productDto);
		
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Product added Successfully.");
			structure.setData(product2);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(long id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product = dao.findProduct(id);
		
		if(product!=null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Product Found.");
			structure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.FOUND);
		}else {
			throw new ProductNotFoundByIdException("Failed to find Product!");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(ProductDto productDto, long id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product2 = dao.updateProduct(productDto, id);
		
		if(product2!=null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Product updated successfully.");
			structure.setData(product2);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.FOUND);
		}else {
			throw new ProductNotFoundByIdException("Failed to update Product!");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(long id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product = dao.deleteProduct(id);
		
		if(product!=null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Product deleted successfully.");
			structure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.FOUND);
		}else {
			throw new ProductNotFoundByIdException("Failed to delete Product!");
		}
	}
}
