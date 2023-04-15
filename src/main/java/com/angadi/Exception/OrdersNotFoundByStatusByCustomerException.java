package com.angadi.Exception;

@SuppressWarnings("serial")
public class OrdersNotFoundByStatusByCustomerException extends RuntimeException {
	private String message;

	public OrdersNotFoundByStatusByCustomerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
