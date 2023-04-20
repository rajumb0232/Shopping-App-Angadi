package com.angadi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.entity.SelectedProduct;
import com.angadi.service.SelectedProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/selectedProduct")
public class SelectedProductController {
	
	@Autowired
	private SelectedProductService selectedProductService;
	
	@ApiOperation(value = "Add selectedProduct", notes = "API used to Save selectedProduct to the database.")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Customer Saved successfully!"),
			@ApiResponse(code = 404, message = "Failed to save Customer!")
			
	})
	@PostMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> addSelectedProduct(@RequestParam
			long productId, @RequestParam long customerId, @RequestParam int productQuantity){
	return selectedProductService.addSelectedProduct(productId,customerId,productQuantity);
	}
	
	@ApiOperation(value = "Get selectedProduct", notes = "API used to fetch selectedProduct based on the selectedProductId.")
	@ApiResponses({
			@ApiResponse(code = 302, message = "selectedProduct Found."),
			@ApiResponse(code = 404, message = "Failed to Fetch selectedProduct!")
	})
	@GetMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> getSelectedProduct(@RequestParam int selectedProductId){
		return selectedProductService.getSelectedProduct(selectedProductId);
	}
	
	@ApiOperation(value = "Update selectedProduct", notes = "API used to Update selectedProduct data based on the selectedProductId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "selectedProduct updated Successfully."),
			@ApiResponse(code = 404, message = "Failed to update selectedProduct!")
	})
	@PutMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> updateSelectedProductQuantity(@RequestParam int selectedProductQuantity, @RequestParam long selectedProductId){
		return selectedProductService.updateSelectedProduct(selectedProductQuantity,selectedProductId);
	}
	
	@ApiOperation(value = "Delete selectedProduct", notes = "API used to Delete selectedProduct based on the selectedProductId.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "selectedProduct Deleted successfully."),
			@ApiResponse(code = 404, message = "Failed to Delete selectedProduct!")
	})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> deleteSelectedProduct(@RequestParam long selectedProductId){
		return selectedProductService.deleteSelectedProduct(selectedProductId);
	}
}
