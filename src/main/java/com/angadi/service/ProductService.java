package com.angadi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.dao.ProductDao;
import com.angadi.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;

	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product2 = dao.addProduct(product);
		
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Product added Successfully.");
			structure.setData(product2);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
	}
}
