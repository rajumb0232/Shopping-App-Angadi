package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.CategoryNotFoundByIdException;
import com.angadi.Exception.ProductNotFoundByIdException;
import com.angadi.Exception.ShopNotFoundWithIdException;
import com.angadi.dao.CategoryDao;
import com.angadi.dao.ProductDao;
import com.angadi.dao.ShopDao;
import com.angadi.dto.ProductDto;
import com.angadi.entity.Category;
import com.angadi.entity.Product;
import com.angadi.entity.Shop;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private CategoryDao categoryDao;

	public ResponseEntity<ResponseStructure<Product>> addProduct(ProductDto productDto, int shopId, int categoryId) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductPrice(productDto.getProductPrice());
		product.setStockQuantity(productDto.getStockQuantity());
		product.setPrimecategory(productDto.getPrimecategory());
		
		Shop shop =shopDao.getShop(shopId);
		if(shop==null) {
			throw new ShopNotFoundWithIdException("Failed to add product, no such shop found!");
		}else {
			List<Product> products = shop.getProducts();
			// adding product to products list of shop
			products.add(product);
			
			Category category = categoryDao.getCategoryById(categoryId);
			if(category!=null) {
				// setting shop to product
				product.setShop(shop);
				// adding product to product list of category
				category.getProducts().add(product);
				Product product2 =  dao.addProduct(product);
				shopDao.updateShop(shop, shop.getShopId());
				categoryDao.updateCategory(category, category.getCategoryId());
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Product added Successfully.");
				structure.setData(product2);
				return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
				
			}
			throw new CategoryNotFoundByIdException("Failed to add Product, no such Category Found!");
		}
	
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(long id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product = dao.findProduct(id);
		
		if(product!=null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Product Found.");
			structure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.FOUND);
		}else {
			throw new ProductNotFoundByIdException("Failed to find Product!");
		}
	}

	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(ProductDto productDto, long id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product2 = dao.findProduct(id);
		
		if(product2!=null) {
			product2.setProductId(id);
			product2.setProductName(productDto.getProductName());
			product2.setProductDescription(productDto.getProductDescription());
			product2.setProductPrice(productDto.getProductPrice());
			product2.setStockQuantity(productDto.getStockQuantity());;
			product2.setPrimecategory(productDto.getPrimecategory());
			
			dao.updateProduct(product2);
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Product updated successfully.");
			structure.setData(product2);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}else {
			throw new ProductNotFoundByIdException("Failed to update Product!");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(long id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product = dao.deleteProduct(id);
		
		if(product!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Product deleted successfully.");
			structure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}else {
			throw new ProductNotFoundByIdException("Failed to delete Product!");
		}
	}
}
