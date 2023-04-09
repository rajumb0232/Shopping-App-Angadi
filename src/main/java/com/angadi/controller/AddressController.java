package com.angadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponceStructure;
import com.angadi.entity.Address;
import com.angadi.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<ResponceStructure<Address>> saveAddress(@RequestBody Address address ){
		return service.saveAddress(address);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<ResponceStructure<Address>> getAddress(@PathVariable long addressId){
		return service.getAddress(addressId);
	}
	
	@GetMapping
	public ResponseEntity<ResponceStructure<List<String>>> getAllAreaByPincode(@RequestParam int pincode){
		return service.getAllAreaByPincode(pincode);
	}
}
