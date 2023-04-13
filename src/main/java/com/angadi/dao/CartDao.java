package com.angadi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Cart;
import com.angadi.repository.CartRepo;

@Repository
public class CartDao {
	
	@Autowired
	private CartRepo repo;
	
	public Cart addCart(Cart cart) {
		return repo.save(cart);
	}
}
