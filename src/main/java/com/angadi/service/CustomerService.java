package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.CustomerNotFoundByEmailException;
import com.angadi.Exception.CustomerNotFoundByIdException;
import com.angadi.dao.CartDao;
import com.angadi.dao.CustomerDao;
import com.angadi.dao.CustomerOrderDao;
import com.angadi.dao.SelectedProductDao;
import com.angadi.dto.CustomerDto;
import com.angadi.entity.Address;
import com.angadi.entity.Cart;
import com.angadi.entity.Customer;
import com.angadi.entity.CustomerOrder;
import com.angadi.entity.SelectedProduct;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerOrderDao customerOrderDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private SelectedProductDao selectedProductDao;
	@Autowired
	private CustomerDao customerDao;
	
	public ResponseEntity<ResponseStructure<CustomerDto>> addCustomer(Customer customer) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		// creating a cart for Customer
		Cart cart = cartDao.addCart(new Cart());
		customer.setCart(cart);
		Customer customer2 = customerDao.addCustomer(customer);
		
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
		Customer customer = customerDao.getCustomer(id);
		
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

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(Customer customer, long customerId) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer existing = customerDao.getCustomer(customerId);
		
		if(existing!=null) {
			Address address = customer.getAddress();
			if(address!=null) {
				// checking if there is any existing address
				// if present update existing address.
				if(existing.getAddress()!=null) {
					address.setAddressId(existing.getAddress().getAddressId());
					
				}
			}
			Customer customer2 = customerDao.updateCustomer(customer, customerId);
			CustomerDto dto = new CustomerDto();
			dto.setCustomerId(customer2.getCustomerId());
			dto.setCustomerName(customer2.getCustomerName());
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Customer updated successfully.");
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		}else {
			throw new CustomerNotFoundByIdException("Failed to update Customer!");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(long customerId) {
		ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
		Customer customer = customerDao.getCustomer(customerId);
		/*
		 * cascading done for address and cart*/
		if(customer!=null) {
			// deleting all the selectedProduct in cart
			List<SelectedProduct> existingSelectedProducts = customer.getCart().getSelectedProducts();
			if(existingSelectedProducts.size()>0) {
				for(SelectedProduct selectedProduct : existingSelectedProducts) {
					selectedProduct.setProduct(null);
					selectedProductDao.deleteSelectedProduct(selectedProduct);
				}
			}

			
			// deleting all the customerOrders and related selected products
			List<CustomerOrder> existingOrders = customer.getOrders();
			if(existingOrders.size()>0) {
				for(CustomerOrder customerOrder : existingOrders) {
					SelectedProduct selectedProduct = customerOrder.getSelectedProduct();
					if(selectedProduct!=null) {
						customerOrder.setSelectedProduct(null);
						selectedProduct.setProduct(null);
					}
					customerOrder.setShop(null);
					customerOrderDao.deleteCustomerOrder(customerOrder);
					selectedProductDao.deleteSelectedProduct(selectedProduct);
				}
			}
			customerDao.deleteCustomer(customer);
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
		Customer customer = customerDao.getCustomerByEmail(email);
		
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
		Customer customer= customerDao.setAddressToCustomer(address, id);
		
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
		Customer customer= customerDao.resetAddressToCustomer(address, id);
		
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
