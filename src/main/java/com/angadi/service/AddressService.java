package com.angadi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.AddressNotFoundByIdException;
import com.angadi.Exception.ShopsNotFoundInRequestedAreaException;
import com.angadi.dao.AddressDao;
import com.angadi.dto.ShopDto;
import com.angadi.entity.Address;
import com.angadi.entity.Shop;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Address>> getAddress(long addressId) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Address address = addressDao.getAddress(addressId);

		if (address != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Address found.");
			structure.setData(address);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		} else {
			throw new AddressNotFoundByIdException("Failed to find Address!");
		}
	}

	public ResponseEntity<ResponseStructure<List<String>>> getAllAreaByPincode(int pincode) {
		ResponseStructure<List<String>> structure = new ResponseStructure<>();
		List<Address> addresses = addressDao.getAllAreaByPincode(pincode);
		
		if(addresses!=null) {
			List<String> areas = new ArrayList<>();
			for(Address address : addresses) {
				areas.add(address.getArea());
			}
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("All areas found.");
			structure.setData(areas);
			return new ResponseEntity<ResponseStructure<List<String>>>(structure, HttpStatus.FOUND);
		}else {
			throw new AddressNotFoundByIdException("Failed to find Areas with pincode!");
		}
	}

	public ResponseEntity<ResponseStructure<List<ShopDto>>> getAllShopByArea(String area) {
		ResponseStructure<List<ShopDto>> structure = new ResponseStructure<>();
		List<Address> addresses = addressDao.getAllShopByArea(area);
		
		if(addresses!=null) {
			List<ShopDto> shops = new ArrayList<>();
			for(Address address : addresses) {
				Shop shop = address.getShop();
				if(shop!=null) {
					ShopDto dto = new ShopDto();
					dto.setShopId(shop.getShopId());
					dto.setShopName(shop.getShopName());
					dto.setShopDescription(shop.getShopDescription());
					dto.setPrimeCategory(shop.getPrimeCategory());
					shops.add(dto);
				}else {
					throw new ShopsNotFoundInRequestedAreaException("Failed to find Shops!");
				}
				
			}
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("All available shops found!");
			structure.setData(shops);
			return new ResponseEntity<ResponseStructure<List<ShopDto>>> (structure, HttpStatus.FOUND);
		}else {
			throw new ShopsNotFoundInRequestedAreaException("Failed to find Shops!");
		}
	}

	
	

	
}
