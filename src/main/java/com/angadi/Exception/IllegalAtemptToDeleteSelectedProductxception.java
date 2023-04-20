package com.angadi.Exception;

@SuppressWarnings("serial")
public class IllegalAtemptToDeleteSelectedProductxception extends RuntimeException {
	private String message;

	public IllegalAtemptToDeleteSelectedProductxception(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
