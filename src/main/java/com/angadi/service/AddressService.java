package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponseStructure;
import com.angadi.Exception.AddressNotFoundByIdException;
import com.angadi.Exception.AreasNotfoundWithGivenPincodeException;
import com.angadi.Exception.ShopsNotFoundInRequestedAreaException;
import com.angadi.dao.AddressDao;
import com.angadi.dto.ShopDto;
import com.angadi.entity.Address;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;


	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		addressDao.saveAddress(address);

		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Address added successfully!");
		structure.setData(address);
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);

	}

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
		List<String> areas = addressDao.getAllAreaByPincode(pincode);
		
		if(areas.isEmpty()) {
			throw new AreasNotfoundWithGivenPincodeException("failed to find Areas!");
			
		}else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("All areas found.");
			structure.setData(areas);
			return new ResponseEntity<ResponseStructure<List<String>>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<ShopDto>>> getAllShopByArea(String area) {
		ResponseStructure<List<ShopDto>> structure = new ResponseStructure<>();
		List<ShopDto> shops = addressDao.getAllShopByArea(area);
		
		if(shops.isEmpty()) {
			throw new ShopsNotFoundInRequestedAreaException("Failed to find Shops!");
		}else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("All available shops found!");
			structure.setData(shops);
			return new ResponseEntity<ResponseStructure<List<ShopDto>>> (structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address, long addressId) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Address address2 = addressDao.updateAddress(addressId, address);
		
		if(address2!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Address successfully updated.");
			structure.setData(address2);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}else {
			throw new AddressNotFoundByIdException("failed to update Address!");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(long addressId) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Address address2 = addressDao.deleteAddress(addressId);
		
		if(address2!=null) {
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Address successfully deleted.");
			structure.setData(address2);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}else {
			throw new AddressNotFoundByIdException("failed to delete Address!");
		}
	}
	
	

	
}
