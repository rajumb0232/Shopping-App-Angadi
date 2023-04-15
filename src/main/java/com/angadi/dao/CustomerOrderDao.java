package com.angadi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Customer;
import com.angadi.entity.CustomerOrder;
import com.angadi.enums.OrderStatus;
import com.angadi.repository.CustomerOrderRepo;

@Repository
public class CustomerOrderDao {
	
	@Autowired
	private CustomerOrderRepo customerOrderRepo;
	
	public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
		return customerOrderRepo.save(customerOrder);
	}
	
	public CustomerOrder getCustomerOrder(int orderId) {
		Optional<CustomerOrder> optional = customerOrderRepo.findById(orderId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public CustomerOrder updateCustomerOrder(CustomerOrder customerOrder) {
		return customerOrderRepo.save(customerOrder);
	}
	
	public CustomerOrder deleteCustomerOrder(int orderId) {
		Optional<CustomerOrder> optional = customerOrderRepo.findById(orderId);
		if(optional.isEmpty()) {
			return null;
		}else {
			customerOrderRepo.delete(optional.get());
			return optional.get();
		}
	}

	public List<CustomerOrder> getCustomerOrderByOrderStatusByCustomer(Customer customer, OrderStatus orderStatus) {
	  Optional<List<CustomerOrder>> optional = customerOrderRepo.findAllByOrderStatusByCustomer(customer, orderStatus);
	  if(optional.isEmpty()) {
		  return null;
	  }else {
		  return optional.get();
	  }
	}

	public List<CustomerOrder> getCustomerOrderByOrderStatus(OrderStatus orderStatus) {
		Optional<List<CustomerOrder>> optional = customerOrderRepo.getCustomerOrderByOrderStatus(orderStatus);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}

}
