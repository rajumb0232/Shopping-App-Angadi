package com.angadi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.Exception.ShopNotFoundWithIdException;
import com.angadi.dto.ProductDto;
import com.angadi.entity.Product;
import com.angadi.entity.Shop;
import com.angadi.repository.ProductRepo;
import com.angadi.repository.ShopRepo;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepo repo;
	@Autowired
	private ShopRepo shopRepo;
	
	public Product addProduct(ProductDto productDto) {
		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductPrice(productDto.getProductPrice());
		product.setStockQuantity(productDto.getStockQuantity());
		product.setPrimecategory(productDto.getPrimecategory());
		
		Optional<Shop> optional = shopRepo.findById(productDto.getShopId());
		if(optional.isEmpty()) {
			throw new ShopNotFoundWithIdException("Failed to add product, no such shop found!");
		}else {
			Shop shop = optional.get();
			shop.setAddress(optional.get().getAddress());
			product.setShop(shop);
		}
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
	
	public Product updateProduct(ProductDto productDto, long id) {
		Optional<Product> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			Product product = optional.get();
			product.setProductId(id);
			product.setProductName(productDto.getProductName());
			product.setProductDescription(productDto.getProductDescription());
			product.setProductPrice(productDto.getProductPrice());
			product.setStockQuantity(productDto.getStockQuantity());
			product.setPrimecategory(productDto.getPrimecategory());
			
			Optional<Shop> optional2 = shopRepo.findById(productDto.getShopId());
			if(optional.isEmpty()) {
				throw new ShopNotFoundWithIdException("Failed to add product, no such shop found!");
			}else {
				Shop shop = optional2.get();
				shop.setAddress(optional2.get().getAddress());
				product.setShop(shop);
			}
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
