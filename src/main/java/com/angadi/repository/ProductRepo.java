package com.angadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
