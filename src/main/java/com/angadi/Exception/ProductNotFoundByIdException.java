package com.angadi.Exception;

@SuppressWarnings("serial")
public class ProductNotFoundByIdException extends RuntimeException {
	private String message;

	public ProductNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
