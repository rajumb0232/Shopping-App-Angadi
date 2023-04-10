package com.angadi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.angadi.Configuration.ResponseStructure;

@RestControllerAdvice
public class AngadiExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	// ******************* for entity Address ********************
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressNotfoundById(AddressNotFoundByIdException ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessage());
		structure.setData("Address not found with the requested Id!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> areasNotfoundWithGivenPincode(AreasNotfoundWithGivenPincodeException ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessage());
		structure.setData("No areas found with the requested pincode!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> shopsNotfoundInRequestedArea(ShopsNotFoundInRequestedAreaException ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessage());
		structure.setData("No shops found with the requested area!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	
	// ******************* for entity merchant *********************
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> MerchantNotFoundBy(MerchantNotFoundByIdException ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessage());
		structure.setData("Merchant not present with the requested Id!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	
	// ******************* for entity Shop ***********************
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ShopNotFoundWithId(ShopNotFoundWithIdException ex){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMessage());
		structure.setData("Shop not present with the requested Id!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
