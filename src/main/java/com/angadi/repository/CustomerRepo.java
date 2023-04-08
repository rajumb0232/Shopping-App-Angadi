package com.angadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
