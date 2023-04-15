package com.angadi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.angadi.entity.Customer;
import com.angadi.entity.CustomerOrder;
import com.angadi.enums.OrderStatus;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Integer> {
	
	
	@Query("select c from CustomerOrder c where c.customer=?1 and c.orderStatus=?2")
	Optional<List<CustomerOrder>> findAllByOrderStatusByCustomer(Customer customer, OrderStatus orderStatus);
	
	@Query("select c from CustomerOrder c where c.orderStatus=?1")
	Optional<List<CustomerOrder>> getCustomerOrderByOrderStatus(OrderStatus orderStatus);

}
