package com.angadi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Product;
import com.angadi.repository.ProductRepo;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepo repo;
	
	public Product addProduct(Product product) {
		return repo.save(product);
	}
	
	public Product findProduct(long id) {
		Optional<Product> optional = repo.findById(id);
		
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public Product updateProduct(Product product, long id) {
		Optional<Product> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			product.setProductId(id);
			product.setShop(optional.get().getShop());
			return repo.save(product);
		}
	}
	
	public Product deleteProduct(long id) {
		Optional<Product> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			repo.deleteById(id);
			return optional.get();
		}
	}
}
