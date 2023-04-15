package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.AddressNotFoundWithCustomerException;
import com.angadi.Exception.CartisEmptyException;
import com.angadi.Exception.CustomerNotFoundByIdException;
import com.angadi.Exception.OrdersNotFoundByStatusByCustomerException;
import com.angadi.Exception.OrdersNotFoundByStatusException;
import com.angadi.dao.CartDao;
import com.angadi.dao.CustomerDao;
import com.angadi.dao.CustomerOrderDao;
import com.angadi.dao.ProductDao;
import com.angadi.entity.Cart;
import com.angadi.entity.Customer;
import com.angadi.entity.CustomerOrder;
import com.angadi.entity.Product;
import com.angadi.entity.SelectedProduct;
import com.angadi.enums.OrderStatus;

@Service
public class CustomerOrderService {
	
	@Autowired
	private CustomerOrderDao  customerOrderDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<CustomerOrder>> addCustomerOrder(long customerId) {
		Customer customer = customerDao.getCustomer(customerId);
		
		if(customer!=null) {
			if(customer.getAddress()!=null) {
				Cart cart= customer.getCart();
				List<SelectedProduct> selectedProducts = cart.getSelectedProducts();
				if(selectedProducts.isEmpty()) {
					throw new CartisEmptyException("Failed to place Order!");
				}else {
					
					// removing selectedProducts from cart
					cart.setSelectedProducts(null);
					cartDao.updateCart(cart);
					
					// creating customerOrder object and setting values to it
					CustomerOrder customerOrder = new CustomerOrder();
					customerOrder.setSelectedProducts(selectedProducts);
					customerOrder.setOrderStatus(OrderStatus.PROCESSING);
					double totalPrice = 0;
					
					for(SelectedProduct selectedProduct : selectedProducts) {
						totalPrice+=selectedProduct.getTotalPrice();
						
						// resetting the quantity of the product in shop.
						Product product = selectedProduct.getProduct();
						product.setStockQuantity(product.getStockQuantity() - selectedProduct.getProductQuantity());
						productDao.updateProduct(product);
					}
					
					customerOrder.setTotalPrice(totalPrice);
					customerOrder.setCustomer(customer);
					customerOrderDao.addCustomerOrder(customerOrder);
					
					// setting customerOrder to orders list of customer
					List<CustomerOrder> orders = customer.getOrders();
					orders.add(customerOrder);
					customerDao.updateCustomer(customer, customerId);
					
					ResponseStructure<CustomerOrder> structure = new ResponseStructure<>();
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMessage("SelectedProduct added to cart successfully.");
					structure.setData(customerOrder);
					return new ResponseEntity<ResponseStructure<CustomerOrder>>(structure,HttpStatus.CREATED);
				}
			}else {
				throw new AddressNotFoundWithCustomerException("failed to place order!");
			}
			}
			else {
			throw new CustomerNotFoundByIdException("Failed to Place Order!");
			}
	}

	public ResponseEntity<ResponseStructure<List<CustomerOrder>>> getCustomerOrderByOrderStatusByCustomer(OrderStatus orderStatus, long customerId) {
		Customer customer = customerDao.getCustomer(customerId);
		if(customer!=null) {
			 List<CustomerOrder> orders = customerOrderDao.getCustomerOrderByOrderStatusByCustomer(customer, orderStatus);
			 if(orders.isEmpty()) {
				 throw new OrdersNotFoundByStatusByCustomerException("failed to find Orders!");
			 }
				ResponseStructure<List<CustomerOrder>> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("Orders found in status - PROCESSING.");
				structure.setData(orders);
				return new ResponseEntity<ResponseStructure<List<CustomerOrder>>>(structure,HttpStatus.FOUND);
		}else {
			throw new CustomerNotFoundByIdException("failed to get Orders");
		}
	}

	public ResponseEntity<ResponseStructure<List<CustomerOrder>>> getCustomerOrderByOrderStatus(OrderStatus orderStatus) {
		List<CustomerOrder> orders = customerOrderDao.getCustomerOrderByOrderStatus(orderStatus);
		if(orders.isEmpty()) {
			throw new OrdersNotFoundByStatusException("failed to find Orders!");
		}else {
			ResponseStructure<List<CustomerOrder>> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Orders found in status - PROCESSING.");
			structure.setData(orders);
			return new ResponseEntity<ResponseStructure<List<CustomerOrder>>>(structure,HttpStatus.FOUND);
		}
	}
	
	
}
