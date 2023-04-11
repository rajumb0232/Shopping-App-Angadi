package com.angadi.Exception;

@SuppressWarnings("serial")
public class CategoryNotFoundByIdException extends RuntimeException {
	private String message;

	public CategoryNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
}
