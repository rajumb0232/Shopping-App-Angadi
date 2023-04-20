package com.angadi.Exception;

@SuppressWarnings("serial")
public class CannotDeleteDeliveredOrderException extends RuntimeException {
	
	String message;

	public CannotDeleteDeliveredOrderException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
