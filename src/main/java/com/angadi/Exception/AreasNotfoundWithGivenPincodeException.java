package com.angadi.Exception;

@SuppressWarnings("serial")
public class AreasNotfoundWithGivenPincodeException extends RuntimeException {
	private String message;

	public AreasNotfoundWithGivenPincodeException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
