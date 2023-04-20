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
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.angadi.enums.PrimeCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties("customerOrders")
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shopId;
	
	@NotEmpty(message = "Invalid Name!")
	private String shopName;
	
	@Min(6999999999l)
	@Max(9999999999l)
	private long shopPhoneNumber;
	
	@NotEmpty(message = "Invalid Email!")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email!")
	private String shopEmail;
	
	private String shopDescription;
	
	private PrimeCategory primeCategory;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	@JsonManagedReference(value = "shop_address")
//	@JsonIgnore
	private Address address;
	
	@OneToMany(mappedBy = "shop",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Product> products;
	
	@ManyToOne
	@JoinColumn
//	@JsonIgnoreProperties("shops")
	@JsonIgnore
	private Merchant merchant;
	
	/**
	 * If a shop has to be deleted, all the customer order Status 
	 * should be DELIVERED.*/
	
	@OneToMany(mappedBy = "shop")
	@JsonIgnore
	private List<CustomerOrder> customerOrders;

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

	public List<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrder(List<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}


	
	
	
}
