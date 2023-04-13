package com.angadi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.CustomerNotFoundByEmailException;
import com.angadi.Exception.CustomerNotFoundByIdException;
import com.angadi.dao.CartDao;
import com.angadi.dao.CustomerDao;
import com.angadi.dto.CustomerDto;
import com.angadi.entity.Address;
import com.angadi.entity.Cart;
import com.angadi.entity.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao dao;
	@Autowired
	private CartDao cartDao;

	public ResponseEntity<ResponseStructure<CustomerDto>> addCustomer(Customer customer) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		// creating a cart for Customer
		Cart cart = cartDao.addCart(new Cart());
		customer.setCart(cart);
		Customer customer2 = dao.addCustomer(customer);
		
			CustomerDto dto = new CustomerDto();
			dto.setCustomerId(customer2.getCustomerId());
			dto.setCustomerName(customer2.getCustomerName());
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Customer added successfully.");
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomer(long id) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer customer = dao.getCustomer(id);
		
		if(customer!=null) {
			CustomerDto dto = new CustomerDto();
			dto.setCustomerId(customer.getCustomerId());
			dto.setCustomerName(customer.getCustomerName());
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Customer found.");
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		}else {
			throw new CustomerNotFoundByIdException("Failed to find customer!");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(Customer customer, long id) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer customer2 = dao.updateCustomer(customer, id);
		
		if(customer2!=null) {
			CustomerDto dto = new CustomerDto();
			dto.setCustomerId(customer.getCustomerId());
			dto.setCustomerName(customer.getCustomerName());
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Customer updated successfully.");
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		}else {
			throw new CustomerNotFoundByIdException("Failed to update Customer!");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(long id) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer customer = dao.deleteCustomer(id);
		
		if(customer!=null) {
			CustomerDto dto = new CustomerDto();
			dto.setCustomerId(customer.getCustomerId());
			dto.setCustomerName(customer.getCustomerName());
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Customer deleted successfully.");
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		}else {
			throw new CustomerNotFoundByIdException("Failed to delete customer!");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerByEmailAndPassword(String email, String password) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer customer = dao.getCustomerByEmail(email);
		
		if(customer!=null && customer.getCustomerPassword().equals(password)) {
			CustomerDto dto = new CustomerDto();
			dto.setCustomerId(customer.getCustomerId());
			dto.setCustomerName(customer.getCustomerName());
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Customer found.");
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		}else {
			throw new CustomerNotFoundByEmailException("Failed to find Customer");
		}
	}
	
	public ResponseEntity<ResponseStructure<CustomerDto>> addAddressToCustomer(Address address, long id){
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer customer= dao.setAddressToCustomer(address, id);
		
		if(customer!=null) {
				CustomerDto dto = new CustomerDto();
				dto.setCustomerId(customer.getCustomerId());
				dto.setCustomerName(customer.getCustomerName());
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Added Address to Customer Sucessfully.");
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		}else {
			throw new CustomerNotFoundByIdException("Failed to add Address to Customer!");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> resetAddressToCustomer(Address address, long id) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer customer= dao.resetAddressToCustomer(address, id);
		
		if(customer!=null) {
				CustomerDto dto = new CustomerDto();
				dto.setCustomerId(customer.getCustomerId());
				dto.setCustomerName(customer.getCustomerName());
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Updated Address to Customer Sucessfully.");
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		}else {
			throw new CustomerNotFoundByIdException("Failed to update Address to Customer!");
		}
	}
}
