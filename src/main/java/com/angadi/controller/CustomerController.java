package com.angadi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.angadi.entity.Customer;
import com.angadi.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@ApiOperation(value = "Add Customer", notes = "API used to Save Customer to the database.")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Customer Saved successfully!"),
			@ApiResponse(code = 404, message = "Failed to save Customer!")
			
	})
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> addCustomer(@Validated @RequestBody Customer customer){
		return service.addCustomer(customer);
	}
	
	@ApiOperation(value = "Get Customer", notes = "API used to fetch Customer based on the CustomerId.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Customer Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Customer!")
			
	})
	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomer(@RequestParam long customerId){
		return service.getCustomer(customerId);
	}
	
	@ApiOperation(value = "Login Customer", notes = "API used to fetch Customer based on the CustomerEmail and customerPassword.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Customer Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Customer!")
			
	})
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerByEmail(@RequestParam String customerEmail, @RequestParam String customerPassword){
		return service.getCustomerByEmailAndPassword(customerEmail, customerPassword);
	}
	
	
	@ApiOperation(value = "Update Customer", notes = "API used to Update Customer data based on the CustomerId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Customer updated Successfully."),
			@ApiResponse(code = 404, message = "Failed to update Customer!")
			
	})
	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@Validated @RequestBody Customer customer, @RequestParam long customerId){
		return service.updateCustomer(customer, customerId);
	}
	
	
	@ApiOperation(value = "Delete Customer", notes = "API used to Delete Customer based on the CustomerId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Customer Deleted successfully."),
			@ApiResponse(code = 404, message = "Failed to Delete Customer!")
			
	})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(@RequestParam long customerId){
		return service.deleteCustomer(customerId);
	}
}
