package com.angadi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.AddressNotFoundWithCustomerException;
import com.angadi.Exception.CannotDeleteDeliveredOrderException;
import com.angadi.Exception.CartisEmptyException;
import com.angadi.Exception.CustomerNotFoundByIdException;
import com.angadi.Exception.OrdersNotFoundByStatusByCustomerException;
import com.angadi.Exception.OrdersNotFoundByStatusException;
import com.angadi.Exception.orderNotFoundByIdException;
import com.angadi.dao.CartDao;
import com.angadi.dao.CustomerDao;
import com.angadi.dao.CustomerOrderDao;
import com.angadi.dao.ProductDao;
import com.angadi.dao.SelectedProductDao;
import com.angadi.dao.ShopDao;
import com.angadi.entity.Cart;
import com.angadi.entity.Customer;
import com.angadi.entity.CustomerOrder;
import com.angadi.entity.Product;
import com.angadi.entity.SelectedProduct;
import com.angadi.entity.Shop;
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
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private SelectedProductDao selectedProductDao;

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
					
					List<CustomerOrder> recentOrders = new ArrayList<>();
					List<CustomerOrder> orders = customer.getOrders();
					for(SelectedProduct selectedProduct : selectedProducts) {
						
						// creating customerOrder object and setting values to it
						CustomerOrder customerOrder = new CustomerOrder();
						customerOrder.setSelectedProduct(selectedProduct);
						customerOrder.setOrderStatus(OrderStatus.PROCESSING);
						customerOrder.setTotalPrice(selectedProduct.getTotalPrice());
						customerOrder.setCustomer(customer);
						recentOrders.add(customerOrder);
						
						//adding order to shop's customer order list
						Shop shop = selectedProduct.getProduct().getShop();
						shop.getCustomerOrders().add(customerOrder);
						customerOrder.setShop(shop);
						customerOrderDao.addCustomerOrder(customerOrder);
						shopDao.addShop(shop); // used to update when there is only change in list of orders.
						
						// resetting the quantity of the product in shop.
						Product product = selectedProduct.getProduct();
						product.setStockQuantity(product.getStockQuantity() - selectedProduct.getProductQuantity());
						productDao.updateProduct(product);
						
						// setting customerOrder to orders list
						orders.add(customerOrder);
						
						// making selectedProduct as null in cart.
						selectedProduct.setCart(null);
						selectedProductDao.updateSelectedProduct(selectedProduct);
					}
					customerDao.updateCustomer(customer, customerId);
					
					ResponseStructure<CustomerOrder> structure = new ResponseStructure<>();
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMessage("SelectedProduct added to cart successfully.");
					structure.setData(recentOrders);
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

	public ResponseEntity<ResponseStructure<CustomerOrder>> updateCustomerOrder(OrderStatus orderStatus, int orderId) {
		CustomerOrder customerOrder = customerOrderDao.updateCustomerOrder(orderStatus,orderId);
		if(customerOrder!=null) {
			ResponseStructure<CustomerOrder> structure = new ResponseStructure<>();
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Orders found in status - PROCESSING.");
			structure.setData(customerOrder);
			return new ResponseEntity<ResponseStructure<CustomerOrder>>(structure,HttpStatus.OK);
		}else {
			throw new orderNotFoundByIdException("Failed to update Order Status!");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerOrder>> deleteCustomerOrder(int orderId) {
		
		CustomerOrder customerOrder = customerOrderDao.getCustomerOrder(orderId);
		if(customerOrder!=null) {
			System.err.println(customerOrder.getOrderStatus());
			if(customerOrder.getOrderStatus()!= OrderStatus.DELIVERED) {
				// increasing back the stockQuatity of the product. 
				Product product = customerOrder.getSelectedProduct().getProduct();
				product.setStockQuantity(product.getStockQuantity()+customerOrder.getSelectedProduct().getProductQuantity());
				
				// deleting the selectedOrder from table
				SelectedProduct selectedProduct = customerOrder.getSelectedProduct();
				selectedProduct.setProduct(null);
				
				// deleting the order.
				customerOrderDao.deleteCustomerOrder(customerOrder);
				productDao.updateProduct(product);
				selectedProductDao.deleteSelectedProduct(selectedProduct);
				ResponseStructure<CustomerOrder> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("CustomerOrder delete Successfully!");
				structure.setData(customerOrder);
				return new ResponseEntity<ResponseStructure<CustomerOrder>>(structure, HttpStatus.OK);
				
			}else {
				throw new CannotDeleteDeliveredOrderException("Failed to delete order!");
			}
		}else {
			throw new orderNotFoundByIdException("failed to delete order!");
		}
	}

	
	
}
