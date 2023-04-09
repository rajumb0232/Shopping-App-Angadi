package com.angadi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.angadi.Configuration.ResponceStructure;
import com.angadi.entity.Address;

@RestControllerAdvice
public class AngadiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> AddressNotfoundById(AddressNotFoundByIdException ex){
		ResponceStructure<String> structure = new ResponceStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessage());
		structure.setData("Address not found with the requested Id");
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponceStructure<String>> AreasNotfoundWithGivenPincode(AreasNotfoundWithGivenPincodeException ex){
		ResponceStructure<String> structure = new ResponceStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessage());
		structure.setData("No areas found with the requested pincode");
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
