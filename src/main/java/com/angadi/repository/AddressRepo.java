package com.angadi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.angadi.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{
	
	@Query("select a from Address a where a.area=?1 and a.shop is not null")
	public Optional<List<Address>> getAllByArea(String area);
	
	@Query("select a from Address a where a.pincode=?1 and a.shop is not null")
	public Optional<List<Address>> getAllByPincode(int pincode);
}
