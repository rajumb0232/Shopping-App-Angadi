package com.angadi.dto;

import org.springframework.stereotype.Component;

import com.angadi.enums.PrimeCategory;
@Component
public class ShopDto {
	private int shopId;
	private String shopName;
	private String shopDescription;
	private PrimeCategory primeCategory;
	
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
	public String getShopDescription() {
		return shopDescription;
	}
	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
	public PrimeCategory getPrimeCategory() {
		return primeCategory;
	}
	public void setPrimeCategory(PrimeCategory primeCategory) {
		this.primeCategory = primeCategory;
	}
	
	
}
