package com.angadi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long merchantId;

	@NotEmpty(message = "Enter Valid Name!")
	private String merchantName;

	// @Pattern(regexp = "^[789]\\d{9}$", message = "Invalid Phone Number!")
	@Min(6999999999l)
	@Max(9999999999l)
	private long merchantPhoneNumber;

	@NotEmpty(message = "Invalid Email!")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email!")
	private String merchantEmail;
	
	@NotNull(message = "Merchant Password cannot be Null!")
	@NotBlank(message = "Merchant Password Cannot be Blank!")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
	message = "8 characters mandatory(1 upperCase,1 lowerCase,1 special Character,1 number)")
	private String merchantPassword;

	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("merchant")
	@JsonIgnore
	private List<Shop> shops;

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public long getMerchantPhoneNumber() {
		return merchantPhoneNumber;
	}

	public void setMerchantPhoneNumber(long merchantPhoneNumber) {
		this.merchantPhoneNumber = merchantPhoneNumber;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

}
