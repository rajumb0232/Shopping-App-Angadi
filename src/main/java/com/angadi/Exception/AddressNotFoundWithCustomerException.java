package com.angadi.Exception;

@SuppressWarnings("serial")
public class AddressNotFoundWithCustomerException extends RuntimeException {
	
	private String message;

	public AddressNotFoundWithCustomerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
