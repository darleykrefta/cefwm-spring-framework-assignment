package com.utfpr.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.delivery.entity.Order;
import com.utfpr.delivery.entity.OrderItem; 

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	public OrderItem findByUuid(String uuid);

	public List<OrderItem> findByOrder(Order order);

}
