package com.angadi.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "selectedproducts")
@JsonIgnoreProperties("cart")
public class SelectedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	private double totalPrice;
	private int productQuantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Product product;

	@ManyToOne
	@JoinColumn
	@JsonIgnoreProperties("selectedProducts")
	private Cart cart;
	
	@OneToMany(mappedBy = "selectedProduct", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<CustomerOrder> customerOrders;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(List<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}



	
	
}
