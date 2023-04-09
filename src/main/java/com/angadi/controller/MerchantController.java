package com.angadi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/merchant")
public class MerchantController {
	
	@Autowired
	private MerchantService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> addMerchant(@RequestBody Merchant merchant){
		return service.addMerchant(merchant);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> getMerchant(@RequestParam long id){
		return service.getmerchant(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> updateMerchant(@RequestBody Merchant merchant, @RequestParam long id){
		return service.updateMerchant(merchant, id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MerchantDto>> deleteMerchant(@RequestParam long id){
		return service.deleteMerchant(id);
	}
}
