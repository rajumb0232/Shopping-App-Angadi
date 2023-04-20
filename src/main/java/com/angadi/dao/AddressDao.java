package com.angadi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angadi.entity.Address;
import com.angadi.repository.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;


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
	public List<Address> getAllAreaByPincode(int pincode){
		Optional<List<Address>> optional =addressRepo.getAllByPincode(pincode);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
		
	}
	
	// returns a list of shops present in a particular area.
	public List<Address> getAllShopByArea(String area){
		Optional<List<Address>> optional = addressRepo.getAllByArea(area);
		
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
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
