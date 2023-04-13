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
		Product product2 = repo.save(product);
		return product2;
	}
	
	public Product findProduct(long id) {
		Optional<Product> optional = repo.findById(id);
		
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public Product updateProduct(Product product) {
//		Optional<Product> optional = repo.findById(id);
//		if(optional.isEmpty()) {
//			return null;
//		}else {
//			Product product = optional.get();
////			product.setProductId(id);
////			product.setProductName(productDto.getProductName());
////			product.setProductDescription(productDto.getProductDescription());
////			product.setProductPrice(productDto.getProductPrice());
////			product.setStockQuantity(productDto.getStockQuantity());
////			product.setPrimecategory(productDto.getPrimecategory());
//			
//			Optional<Shop> optional2 = shopRepo.findById(productDto.getShopId());
//			if(optional.isEmpty()) {
//				throw new ShopNotFoundWithIdException("Failed to add product, no such shop found!");
//			}else {
//				Shop shop = optional2.get();
//				shop.setAddress(optional2.get().getAddress());
//				product.setShop(shop);
//			}
			return repo.save(product);
//		}
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
