package com.angadi.Exception;

@SuppressWarnings("serial")
public class CustomerNotFoundByEmailException extends RuntimeException {
	private String message;

	public CustomerNotFoundByEmailException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
