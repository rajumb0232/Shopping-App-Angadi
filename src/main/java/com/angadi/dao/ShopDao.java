package com.angadi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Shop;
import com.angadi.repository.ShopRepo;

@Repository
public class ShopDao {
	
	@Autowired
	private ShopRepo repo;
	
	public Shop addShop(Shop shop) {
		return repo.save(shop);
	}
	
	public Shop getShop(int id) {
		Optional<Shop> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public Shop updateShop(Shop shop, int id) {
		Optional<Shop> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			shop.setShopId(id);
			return repo.save(shop);
		}
	}
	
	public Shop deleteShop(int id) {
		Optional<Shop> optional = repo.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			repo.delete(optional.get());
			return optional.get();
		}
	}
	
}
