package com.angadi.dto;

import com.angadi.enums.PrimeCategory;

public class CategoryDto {
	private int categoryId;
	private PrimeCategory primeCategory;
	private String categoryName;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public PrimeCategory getPrimeCategory() {
		return primeCategory;
	}
	public void setPrimeCategory(PrimeCategory primeCategory) {
		this.primeCategory = primeCategory;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
