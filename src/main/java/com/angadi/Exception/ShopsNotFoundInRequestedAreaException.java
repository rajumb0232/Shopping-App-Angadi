package com.angadi.Exception;

@SuppressWarnings("serial")
public class ShopsNotFoundInRequestedAreaException extends RuntimeException {
	
	private String message;

	public ShopsNotFoundInRequestedAreaException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
