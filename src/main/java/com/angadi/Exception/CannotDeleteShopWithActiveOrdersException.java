package com.angadi.Exception;

@SuppressWarnings("serial")
public class CannotDeleteShopWithActiveOrdersException extends RuntimeException {
	private String message;

	public CannotDeleteShopWithActiveOrdersException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
