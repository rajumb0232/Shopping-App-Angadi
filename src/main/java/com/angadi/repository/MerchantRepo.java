package com.angadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Merchant;

public interface MerchantRepo extends JpaRepository<Merchant, Long>{

}
