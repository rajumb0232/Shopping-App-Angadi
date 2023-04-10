package com.angadi.Exception;

public class ShopNotFoundWithIdException extends RuntimeException {
	
	private String message;

	public ShopNotFoundWithIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
