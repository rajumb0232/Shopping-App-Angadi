package com.angadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.dto.ShopDto;
import com.angadi.entity.Address;
import com.angadi.service.AddressService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@ApiOperation(value = "Get Address by AddressId", notes = "API is used to fetch the address by its Id.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Address Found."),
			@ApiResponse(code = 404, message = "Address Not Found with the requested Id!")
			
	})
	@GetMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> getAddress(@PathVariable long addressId){
		return service.getAddress(addressId);
	}
	
	@ApiOperation(value = "Get Areas by pincode", notes = "API is used to fetch the Areas using the pincode, that have been registered into the app.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Areas Found."),
			@ApiResponse(code = 404, message = "Areas Not Found with the requested Pincode!")
			
	})
	@GetMapping("/areas")
	public ResponseEntity<ResponseStructure<List<String>>> getAllAreaByPincode(@Validated @RequestParam int pincode){
		return service.getAllAreaByPincode(pincode);
	}
	
	@ApiOperation(value = "Get All shops by Area", notes = "API is used to fetch the Shops using the Area name, that have been registered into the app.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Shops Found."),
			@ApiResponse(code = 404, message = "Shops Not Found in the requested Area!")
			
	})
	@GetMapping("/areas/shops")
	public ResponseEntity<ResponseStructure<List<ShopDto>>> getAllShopByArea(@RequestParam String area){
		return service.getAllShopByArea(area);
	}
	
}
