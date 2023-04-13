package com.angadi.Exception;

@SuppressWarnings("serial")
public class CustomerNotFoundByIdException extends RuntimeException {
	public String message;

	public CustomerNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
