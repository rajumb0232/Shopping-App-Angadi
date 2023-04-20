package com.angadi.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.angadi.enums.PrimeCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@NotNull(message = "primeCategory Cannot be null!")
	private PrimeCategory primeCategory;
	
	@NotEmpty(message = "Invalid Name!")
	private String categoryName;
	
	@OneToMany(mappedBy = "category")
//	@JsonIgnoreProperties("category")
	@JsonIgnore
	private List<Product> products;

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
