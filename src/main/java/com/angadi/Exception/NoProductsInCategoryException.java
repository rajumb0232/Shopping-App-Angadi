package com.angadi.Exception;

@SuppressWarnings("serial")
public class NoProductsInCategoryException extends RuntimeException {
	private String message;

	public NoProductsInCategoryException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
}
