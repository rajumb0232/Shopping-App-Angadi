package com.angadi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Cart;
import com.angadi.entity.SelectedProduct;
import com.angadi.repository.SelectedProductRepo;

@Repository
public class SelectedProductDao {
	
	@Autowired
	private SelectedProductRepo selectedProductRepo;
	
	public SelectedProduct addSelectedProduct(SelectedProduct selectedProduct) {
		return selectedProductRepo.save(selectedProduct);
	}
	
	public SelectedProduct getselectedProduct(long selectedProductId) {
		Optional<SelectedProduct> optional = selectedProductRepo.findById(selectedProductId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public SelectedProduct updateSelectedProduct(SelectedProduct selectedProduct) {
			return selectedProductRepo.save(selectedProduct);
	}
	
	public SelectedProduct deleteSelectedProduct(SelectedProduct selectedProduct) {
		try {
			selectedProductRepo.delete(selectedProduct);
			return selectedProduct;
		} catch (RuntimeException e) {
			return null;
		}
			
			
	}
	
	public SelectedProduct getselectedProductByCart(Cart cart) {
		Optional<SelectedProduct> optional = selectedProductRepo.findByCart(cart);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
