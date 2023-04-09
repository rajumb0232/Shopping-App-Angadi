package com.angadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.entity.Address;
import com.angadi.entity.Shop;
import com.angadi.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address ){
		return service.saveAddress(address);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> getAddress(@PathVariable long addressId){
		return service.getAddress(addressId);
	}
	
	@GetMapping("/areas")
	public ResponseEntity<ResponseStructure<List<String>>> getAllAreaByPincode(@RequestParam int pincode){
		return service.getAllAreaByPincode(pincode);
	}
	
	@GetMapping("/areas/shops")
	public ResponseEntity<ResponseStructure<List<Shop>>> getAllShopByArea(@RequestParam String area){
		return service.getAllShopByArea(area);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address address, @RequestParam long addressId){
		return service.updateAddress(address, addressId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@RequestParam long Id){
		return service.deleteAddressById(Id);
	}
}
