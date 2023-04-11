package com.angadi.Exception;

@SuppressWarnings("serial")
public class ShopNotFoundWithIdException extends RuntimeException {
	
	private String message;

	public ShopNotFoundWithIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
