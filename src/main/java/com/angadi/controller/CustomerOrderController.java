package com.angadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.entity.CustomerOrder;
import com.angadi.enums.OrderStatus;
import com.angadi.service.CustomerOrderService;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerOrder>> addCustomerOrder(@RequestParam long customerId){
		return  customerOrderService.addCustomerOrder(customerId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<CustomerOrder>>> getCustomerOrderByOrderStatusByCustomer(@RequestParam OrderStatus orderStatus, @RequestParam long customerId){
		return customerOrderService.getCustomerOrderByOrderStatusByCustomer(orderStatus,customerId);
	}
	
	@GetMapping("/all/byStatus")
	public ResponseEntity<ResponseStructure<List<CustomerOrder>>> getCustomerOrderByOrderStatus(@RequestParam OrderStatus orderStatus){
		return customerOrderService.getCustomerOrderByOrderStatus(orderStatus);
	}
}
