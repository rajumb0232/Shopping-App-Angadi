package com.angadi.dto;

import com.angadi.enums.PrimeCategory;

public class ProductDto {
	private long productId;
	private String productName;
	private String productDescription;
	private int stockQuantity;
	private double productPrice;
	private PrimeCategory primecategory;

	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public PrimeCategory getPrimecategory() {
		return primecategory;
	}
	public void setPrimecategory(PrimeCategory primecategory) {
		this.primecategory = primecategory;
	}

	
}
