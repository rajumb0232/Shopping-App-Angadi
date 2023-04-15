package com.angadi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.entity.Cart;
import com.angadi.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Cart>> addProductToCart(@RequestParam long productId, @RequestParam long customerId){
		return cartService.addProductToCart(productId,customerId);
	}
	
}
