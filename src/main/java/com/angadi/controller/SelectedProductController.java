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
import com.angadi.dto.ProductDto;
import com.angadi.entity.SelectedProduct;
import com.angadi.service.SelectedProductService;

@RestController
@RequestMapping("/selectedProduct")
public class SelectedProductController {
	
	@Autowired
	private SelectedProductService selectedProductService;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> addSelectedProduct(@RequestBody
			ProductDto product, @RequestParam long customerId, @RequestParam int productQuantity){
	return selectedProductService.addSelectedProduct(product,customerId,productQuantity);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> getSelectedProduct(@RequestParam int selectedProductId){
		return selectedProductService.getSelectedProduct(selectedProductId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> updateSelectedProductQuantity(@RequestParam int selectedProductQuantity, @RequestParam long selectedProductId){
		return selectedProductService.updateSelectedProduct(selectedProductQuantity,selectedProductId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<SelectedProduct>> deleteSelectedProduct(@RequestParam long selectedProductId, @RequestParam long customerId){
		return selectedProductService.deleteSelectedProduct(selectedProductId,customerId);
	}
}
