package com.angadi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.CustomerNotFoundByIdException;
import com.angadi.Exception.ProductNotFoundByIdException;
import com.angadi.dao.CartDao;
import com.angadi.dao.CustomerDao;
import com.angadi.dao.ProductDao;
import com.angadi.entity.Cart;
import com.angadi.entity.Customer;
import com.angadi.entity.Product;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Cart>> addProductToCart(long productId, long customerId) {
		Customer customer =  customerDao.getCustomer(customerId);
		if(customer!=null) {
			Product product = productDao.findProduct(productId);
			if(product!=null) {
				Cart cart = customer.getCart();
//				cart.getProducts().add(product);
				Cart cart2 = cartDao.updateCart(cart);
				ResponseStructure<Cart> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Product added to cart successfully.");
				structure.setData(cart2);
				return new ResponseEntity<ResponseStructure<Cart>>(structure,HttpStatus.CREATED);
			}else {
				throw new ProductNotFoundByIdException("Failed to add product to cart!");
			}
		}
			throw new CustomerNotFoundByIdException("Failed to add product to cart!");
	}
	
}
