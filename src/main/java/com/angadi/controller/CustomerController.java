package com.angadi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.dto.CustomerDto;
import com.angadi.entity.Address;
import com.angadi.entity.Customer;
import com.angadi.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> addCustomer(@RequestBody Customer customer){
		return service.addCustomer(customer);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomer(@RequestParam long id){
		return service.getCustomer(id);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerByEmail(@RequestParam String email, @RequestParam String password){
		return service.getCustomerByEmailAndPassword(email, password);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestBody Customer customer, @RequestParam long id){
		return service.updateCustomer(customer, id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(@RequestParam long id){
		return service.deleteCustomer(id);
	}
	
	@PutMapping("/addAddress")
	public ResponseEntity<ResponseStructure<CustomerDto>> addAddressToCustomer(@RequestBody Address address , @RequestParam long customerId){
		return service.addAddressToCustomer(address, customerId);
	}
	
	@PutMapping("/updateAddress")
	public ResponseEntity<ResponseStructure<CustomerDto>> updateAddressToCustomer(@RequestBody Address address , @RequestParam long customerId){
		return service.resetAddressToCustomer(address, customerId);
	}
}
