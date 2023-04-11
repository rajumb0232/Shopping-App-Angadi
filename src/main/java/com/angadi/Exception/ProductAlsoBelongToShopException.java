package com.angadi.Exception;

@SuppressWarnings("serial")
public class ProductAlsoBelongToShopException extends RuntimeException{
	private String message;

	public ProductAlsoBelongToShopException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
