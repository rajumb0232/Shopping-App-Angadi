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
import com.angadi.entity.Shop;
import com.angadi.service.shopService;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private shopService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Shop>> addShop(@RequestBody Shop shop){
		return service.addShop(shop);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Shop>> getShop(@RequestParam int id){
		return service.getShop(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Shop>> updateShop(@RequestParam int id, @RequestBody Shop shop){
		return service.updateShop(shop, id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Shop>> deleteShop(@RequestParam int id){
		return service.deleteShop(id);
	}
}
