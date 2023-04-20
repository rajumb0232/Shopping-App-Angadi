package com.angadi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Cart;
import com.angadi.entity.SelectedProduct;

public interface SelectedProductRepo extends JpaRepository<SelectedProduct, Long>{
	
//	@Query("select s from SelectedProduct where s.product=?1 and s.cart=?2")
	public Optional<SelectedProduct> findByCart(Cart cart);
}
