package com.angadi.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private double cartPrice;
	
	@OneToMany
	private List<SelectedProduct> selectedProducts;
	
	@OneToOne(mappedBy = "cart")
	private Customer customer;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(double cartPrice) {
		this.cartPrice = cartPrice;
	}

	public List<SelectedProduct> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<SelectedProduct> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	
	
}
