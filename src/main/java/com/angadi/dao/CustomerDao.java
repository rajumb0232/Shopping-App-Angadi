package com.angadi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Address;
import com.angadi.entity.Customer;
import com.angadi.repository.CustomerRepo;

@Repository
public class CustomerDao {
	
	@Autowired
	private CustomerRepo repo;
	
	public Customer addCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer getCustomer(long id) {
		Optional<Customer> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public Customer updateCustomer(Customer customer, long id) {
		Optional<Customer> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			Customer customer2 = optional.get();
			customer.setCustomerId(id);
			customer.setAddress(customer2.getAddress());
			customer.setCart(customer2.getCart());
			return repo.save(customer);
		}
	}
	
	public Customer deleteCustomer(long id) {
		Optional<Customer> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			repo.deleteById(id);
			return optional.get();
		}
	}

	public Customer getCustomerByEmail(String email) {
		Optional<Customer> optional = repo.findByCustomerEmail(email);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public Customer setAddressToCustomer(Address address, long id) {
		Optional<Customer> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			Customer customer = optional.get();
			customer.setAddress(address);
			return repo.save(customer);
		}
	}

	public Customer resetAddressToCustomer(Address address, long id) {
		Optional<Customer> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			Customer customer = optional.get();
			address.setAddressId(customer.getAddress().getAddressId());
			customer.setAddress(address);
			return repo.save(customer);
		}
	}
}
