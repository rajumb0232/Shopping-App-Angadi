package com.angadi.dto;

import org.springframework.stereotype.Component;

@Component
public class MerchantDto {
	private long merchantId;
	private String merchantName;
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
	
	
}
