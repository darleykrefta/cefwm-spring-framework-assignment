package com.utfpr.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.delivery.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	public OrderItem findByUuid(String uuid);
	
}
