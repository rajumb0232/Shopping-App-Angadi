package com.angadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
