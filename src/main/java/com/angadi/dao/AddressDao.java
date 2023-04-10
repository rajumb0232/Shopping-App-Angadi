package com.angadi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.dto.ShopDto;
import com.angadi.entity.Address;
import com.angadi.entity.Shop;
import com.angadi.repository.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private ShopDto dto;

	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}

	public Address getAddress(long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);

		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}
	
	// returns a list of registered area names where shops are available.
	public List<String> getAllAreaByPincode(int pincode){
		List<Address> addresses = (List<Address>) addressRepo.findAllByPincode(pincode);
		
		
			List<String> areas = new ArrayList<>();
			 for(Address address : addresses) {
				 String area = address.getArea();
				 areas.add(area);
			 }
			 return areas;
		
	}
	
	// returns a list of shops present in a particular area.
	public List<ShopDto> getAllShopByArea(String area){
		Optional<List<Address>> optional = addressRepo.findAllByArea(area);
		
		if(optional.isEmpty()) {
			return null;
		}else {
			List<ShopDto> shops = new ArrayList<>();
			for(Address address : optional.get()) {
				Shop shop = address.getShop();
				dto.setShopId(shop.getShopId());
				dto.setShopName(shop.getShopName());
				dto.setPrimeCategory(shop.getPrimeCategory());
				dto.setShopDescription(shop.getShopDescription());
				shops.add(dto);
			}
			return shops;
		}
	}

	public Address updateAddress(long addressId, Address address) {
		Optional<Address> optional = addressRepo.findById(addressId);

		if (optional.isEmpty()) {
			return null;
		} else {
			Address address2 = optional.get();
			address.setAddressId(address2.getAddressId());
			return addressRepo.save(address);
		}
	}
	
	public Address deleteAddress(long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);

		if (optional.isEmpty()) {
			return null;
		} else {
			Address address = optional.get();
			addressRepo.deleteById(addressId);
			return address;
		}
	}

}
