package com.angadi.Exception;

public class MerchantNotFoundByIdException extends RuntimeException {
	private String message;

	public MerchantNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
