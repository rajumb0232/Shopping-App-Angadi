package com.angadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.CustomerOrder;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Integer> {

}
