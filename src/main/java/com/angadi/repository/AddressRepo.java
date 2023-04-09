package com.angadi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{
	

	public Optional<List<Address>> findAllByArea(String area);

	public Iterable<Address> findAllByPincode(int pincode);
}
