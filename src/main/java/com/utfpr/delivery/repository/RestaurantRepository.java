package com.utfpr.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.delivery.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	public Restaurant findByUuid(String uuid);
	
}
