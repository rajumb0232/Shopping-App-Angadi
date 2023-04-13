package com.angadi.Exception;

@SuppressWarnings("serial")
public class SelectedProductNotFoundByIdException extends RuntimeException {
	private String message;

	public SelectedProductNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
