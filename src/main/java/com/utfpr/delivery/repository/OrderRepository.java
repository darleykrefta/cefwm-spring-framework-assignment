package com.utfpr.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.delivery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public Order findByUuid(String uuid);
	
}
