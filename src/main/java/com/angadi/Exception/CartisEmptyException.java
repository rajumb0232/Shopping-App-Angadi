package com.angadi.Exception;

@SuppressWarnings("serial")
public class CartisEmptyException extends RuntimeException {
	
	private String message;

	public CartisEmptyException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
