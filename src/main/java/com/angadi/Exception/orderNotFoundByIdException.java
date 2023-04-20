package com.angadi.Exception;

@SuppressWarnings("serial")
public class orderNotFoundByIdException extends RuntimeException {
	private String message;

	public orderNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
