package com.angadi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angadi.entity.Category;
import com.angadi.enums.PrimeCategory;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
	
	Optional<List<Category>> findAllByPrimeCategory(PrimeCategory category);
}
