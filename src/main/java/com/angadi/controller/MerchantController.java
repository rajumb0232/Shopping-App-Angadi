package com.angadi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.dto.MerchantDto;
import com.angadi.entity.Merchant;
import com.angadi.service.MerchantService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	private MerchantService service;

	@ApiOperation(value = "Add Merchant", notes = "API used to Save Merchant to the database.")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Merchant Saved successfully!"),
			@ApiResponse(code = 404, message = "Failed to save Merchant!")
			
	})
	@PostMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> addMerchant(@Validated @RequestBody Merchant merchant) {
		return service.addMerchant(merchant);
	}

	@ApiOperation(value = "Get Merchant", notes = "API used to fetch Merchant based on the MerchantId.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Merchant Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Merchant!")
			
	})
	@GetMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> getMerchant(@RequestParam long id) {
		return service.getmerchant(id);
	}

	@ApiOperation(value = "Update Merchant", notes = "API used to Update Merchant data based on the CustomerId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Customer updated Successfully."),
			@ApiResponse(code = 404, message = "Failed to update Customer!")
			
	})
	@PutMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> updateMerchant(@Validated @RequestBody Merchant merchant,
			@RequestParam long id) {
		return service.updateMerchant(merchant, id);
	}
	
	@ApiOperation(value = "Delete Merchant", notes = "API used to Delete Merchant based on the MerchantId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Merchant Deleted successfully."),
			@ApiResponse(code = 404, message = "Failed to Delete Merchant!")
			
	})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> deleteMerchant(@RequestParam long id) {
		return service.deleteMerchant(id);
	}
}
