package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.CannotDeleteShopWithActiveOrdersException;
import com.angadi.Exception.MerchantNotFoundByIdException;
import com.angadi.Exception.ShopNotFoundWithIdException;
import com.angadi.dao.CustomerOrderDao;
import com.angadi.dao.MerchantDao;
import com.angadi.dao.ProductDao;
import com.angadi.dao.SelectedProductDao;
import com.angadi.dao.ShopDao;
import com.angadi.entity.Address;
import com.angadi.entity.CustomerOrder;
import com.angadi.entity.Merchant;
import com.angadi.entity.Product;
import com.angadi.entity.SelectedProduct;
import com.angadi.entity.Shop;
import com.angadi.enums.OrderStatus;

@Service
public class shopService {
	
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private CustomerOrderDao customerOrderDao;
	@Autowired
	private SelectedProductDao selectedProductDao;
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Shop>> addShop(Shop shop, long merchantId) {
		ResponseStructure<Shop> structure = new ResponseStructure<>();
		Merchant merchant = merchantDao.getMerchant(merchantId);
		if(merchant!=null) {
			shop.setMerchant(merchant);
			Shop shop2 = shopDao.addShop(shop);
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Shop added Successfully!");
				structure.setData(shop2);
				return new ResponseEntity<ResponseStructure<Shop>>(structure, HttpStatus.CREATED);
		}else {
			throw new MerchantNotFoundByIdException("Failed to add shop!");
		}

	}

	public ResponseEntity<ResponseStructure<Shop>> getShop(int id) {
		ResponseStructure<Shop> structure = new ResponseStructure<>();
		Shop shop = shopDao.getShop(id);
		if(shop!=null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Shop found.");
			structure.setData(shop);
			return new ResponseEntity<ResponseStructure<Shop>>(structure, HttpStatus.FOUND);
		}else {
			throw new ShopNotFoundWithIdException("Failed to find Shop!");
		}
	}

	public ResponseEntity<ResponseStructure<Shop>> updateShop(Shop shop, int id) {
		ResponseStructure<Shop> structure = new ResponseStructure<>();
		Shop shop3 = shopDao.getShop(id);
		if(shop3!=null){
			if(shop3.getAddress()!=null) {
				Address address = shop.getAddress();
				if(address!=null) {
					address.setAddressId(shop3.getAddress().getAddressId());
					shop.setAddress(address);
				}else {
					shop.setAddress(shop3.getAddress());
				}
			}
			
			shop.setMerchant(shop3.getMerchant());
			shop.setProducts(shop3.getProducts());
			Shop shop2 = shopDao.updateShop(shop,id);

				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Shop updated Successfully.");
				structure.setData(shop2);
				return new ResponseEntity<ResponseStructure<Shop>>(structure, HttpStatus.OK);
			
		}
			throw new ShopNotFoundWithIdException("Failed to update Shop!");
		
		
	}

	public ResponseEntity<ResponseStructure<Shop>> deleteShop(int shopId) {
		ResponseStructure<Shop> structure = new ResponseStructure<>();
		Shop shop = shopDao.getShop(shopId);
		
		if(shop!=null) {
			shop.setMerchant(null);
			List<Product> existingProducts = shop.getProducts();
			shop.setProducts(null);
			boolean orderPresent = false;
			for(CustomerOrder customerOrder : shop.getCustomerOrders()) {
				if(customerOrder.getOrderStatus() != OrderStatus.DELIVERED) {
					orderPresent = true;
					break;
				}
			}if(orderPresent) {
				throw new CannotDeleteShopWithActiveOrdersException("Failed to delete Shop!");
			}else {
				
				shop.setCustomerOrder(null);
				for(Product product:existingProducts) {
					product.setCategory(null);
					product.setShop(null);
					List<SelectedProduct> existingselectedProducts = product.getSelectedProduct();
					for(SelectedProduct selectedProduct : existingselectedProducts) {
						selectedProduct.setProduct(null);
						selectedProduct.setCart(null);
						List<CustomerOrder> existingCustomerOrders = selectedProduct.getCustomerOrders();
						for(CustomerOrder customerOrder : existingCustomerOrders) {
								customerOrder.setShop(null);
								customerOrder.setCustomer(null);
								customerOrder.setSelectedProduct(null);
								customerOrderDao.deleteCustomerOrder(customerOrder);
						}
						selectedProductDao.deleteSelectedProduct(selectedProduct);
					}
					productDao.deleteProduct(product);
				}
			shop =	shopDao.deleteShop(shop);
			}
				
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Shop deleted successfully.");
			structure.setData(shop);
			return new ResponseEntity<ResponseStructure<Shop>>(structure, HttpStatus.OK);
		}else {
			throw new ShopNotFoundWithIdException("Failed to delete Shop!");
		}
	}
	
	
	
	
	
}
