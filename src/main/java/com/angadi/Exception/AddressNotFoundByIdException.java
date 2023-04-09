package com.angadi.Exception;

public class AddressNotFoundByIdException extends RuntimeException {
	
	private String message;

	public AddressNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
