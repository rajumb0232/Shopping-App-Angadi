package com.angadi.Exception;

@SuppressWarnings("serial")
public class OrdersNotFoundByStatusException extends RuntimeException {
	private String message;

	public OrdersNotFoundByStatusException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
