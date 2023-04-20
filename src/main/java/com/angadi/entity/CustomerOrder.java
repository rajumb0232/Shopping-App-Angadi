package com.angadi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.angadi.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customerorders")
//@JsonIgnoreProperties("shop")
public class CustomerOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private OrderStatus orderStatus;
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn
//	@JsonIgnore
	private SelectedProduct selectedProduct;

	@ManyToOne
	@JoinColumn
//	@JsonIgnoreProperties("orders")
	@JsonIgnore
	private Customer customer;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Shop shop;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public SelectedProduct getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(SelectedProduct selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
	
}
