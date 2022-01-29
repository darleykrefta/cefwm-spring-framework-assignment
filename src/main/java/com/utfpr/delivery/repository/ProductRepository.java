package com.utfpr.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.delivery.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByUuid(String uuid);
	
}
