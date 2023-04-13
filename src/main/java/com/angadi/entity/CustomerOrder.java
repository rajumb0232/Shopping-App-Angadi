package com.angadi.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.angadi.enums.OrderStatus;

@Entity
@Table(name = "customerorders")
public class CustomerOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private OrderStatus orderStatus;
	private double tatalPrice;
	
	@OneToMany
	private List<SelectedProduct> selectedProducts;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;

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

	public double getTatalPrice() {
		return tatalPrice;
	}

	public void setTatalPrice(double tatalPrice) {
		this.tatalPrice = tatalPrice;
	}

	public List<SelectedProduct> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<SelectedProduct> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
