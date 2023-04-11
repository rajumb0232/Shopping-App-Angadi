package com.angadi.Exception;

@SuppressWarnings("serial")
public class PrimeCategoryNotFoundException extends RuntimeException {
	private String message;

	public PrimeCategoryNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
