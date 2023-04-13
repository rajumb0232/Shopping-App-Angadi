package com.angadi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.angadi.enums.PrimeCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shopId;
	private String shopName;
	private long shopPhoneNumber;
	private String shopEmail;
	private String shopDescription;
	private PrimeCategory primeCategory;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	@JsonManagedReference
	private Address address;
	
	@OneToMany(mappedBy = "shop",fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Product> products;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	@JsonBackReference
	private Merchant merchant;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public long getShopPhoneNumber() {
		return shopPhoneNumber;
	}

	public void setShopPhoneNumber(long shopPhoneNumber) {
		this.shopPhoneNumber = shopPhoneNumber;
	}

	public String getShopEmail() {
		return shopEmail;
	}

	public void setShopEmail(String shopEmail) {
		this.shopEmail = shopEmail;
	}

	public String getShopDescription() {
		return shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public PrimeCategory getPrimeCategory() {
		return primeCategory;
	}

	public void setPrimeCategory(PrimeCategory primeCategory) {
		this.primeCategory = primeCategory;
	}
	
	
	
}
