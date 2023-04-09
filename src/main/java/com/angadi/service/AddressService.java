package com.angadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.angadi.Configuration.ResponceStructure;
import com.angadi.Exception.AddressNotFoundByIdException;
import com.angadi.Exception.AreasNotfoundWithGivenPincodeException;
import com.angadi.dao.AddressDao;
import com.angadi.entity.Address;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponceStructure<Address>> saveAddress(Address address) {
		ResponceStructure<Address> structure = new ResponceStructure<>();
		addressDao.saveAddress(address);

		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Address added successfully!");
		structure.setData(address);
		return new ResponseEntity<ResponceStructure<Address>>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponceStructure<Address>> getAddress(long addressId) {
		ResponceStructure<Address> structure = new ResponceStructure<>();
		Address address = addressDao.getAddress(addressId);

		if (address != null) {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Address found.");
			structure.setData(address);
			return new ResponseEntity<ResponceStructure<Address>>(structure, HttpStatus.FOUND);
		} else {
			throw new AddressNotFoundByIdException("Failed to find Address!");
		}
	}

	public ResponseEntity<ResponceStructure<List<String>>> getAllAreaByPincode(int pincode) {
		ResponceStructure<List<String>> structure = new ResponceStructure<>();
		List<String> areas = addressDao.getAllAreaByPincode(pincode);
		for(String a : areas) {
			System.out.println(a);
		}
		if(areas.isEmpty()) {
			throw new AreasNotfoundWithGivenPincodeException("failed to find Areas!");
			
		}else {
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("All areas found.");
			structure.setData(areas);
			return new ResponseEntity<ResponceStructure<List<String>>>(structure, HttpStatus.FOUND);
		}
	}

	
}
