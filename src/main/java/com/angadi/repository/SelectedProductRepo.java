package com.angadi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Product;
import com.angadi.entity.SelectedProduct;

public interface SelectedProductRepo extends JpaRepository<SelectedProduct, Long>{
	
	public Optional<SelectedProduct> findByProduct(Product product);
}
