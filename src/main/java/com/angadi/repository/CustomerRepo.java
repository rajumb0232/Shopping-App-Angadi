package com.angadi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	public Optional<Customer> findByCustomerEmail(String email);
}
