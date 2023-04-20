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
import com.angadi.entity.Shop;
import com.angadi.service.shopService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private shopService service;
	
	@ApiOperation(value = "Add Shop", notes = "API used to Save Shop to the database.")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Shop Saved successfully!"),
			@ApiResponse(code = 404, message = "Failed to save Shop!")
	})
	@PostMapping
	public ResponseEntity<ResponseStructure<Shop>> addShop(@Validated @RequestBody Shop shop, @RequestParam long merchantId){
		return service.addShop(shop, merchantId);
	}
	
	@ApiOperation(value = "Get Shop", notes = "API used to fetch Customer based on the ShopId.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "Shop Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch Shop!")
	})
	@GetMapping
	public ResponseEntity<ResponseStructure<Shop>> getShop(@RequestParam int shopId){
		return service.getShop(shopId);
	}
	
	@ApiOperation(value = "Update Shop", notes = "API used to Update Shop data based on the ShopId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Shop updated Successfully."),
			@ApiResponse(code = 404, message = "Failed to update Shop!")
	})
	@PutMapping
	public ResponseEntity<ResponseStructure<Shop>> updateShop(@RequestParam int shopId,@Validated @RequestBody Shop shop){
		return service.updateShop(shop, shopId);
	}
	
	@ApiOperation(value = "Delete Shop", notes = "API used to Delete Shop based on the ShopId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Shop Deleted successfully."),
			@ApiResponse(code = 404, message = "Failed to Delete Shop!")
	})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Shop>> deleteShop(@RequestParam int shopId){
		return service.deleteShop(shopId);
	}
}
