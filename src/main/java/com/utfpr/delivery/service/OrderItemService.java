package com.utfpr.delivery.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.OrderItem;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.OrderItemRepository;
import com.utfpr.delivery.repository.OrderRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public List<OrderItem> getAllItemsByOrder(String orderId) {

		return orderItemRepository.findByOrder(orderRepository.findByUuid(orderId));

	}

	public OrderItem getOrderItemByUuid(String uuid) {

		OrderItem orderItem = orderItemRepository.findByUuid(uuid);

		if (orderItem == null) {
			throw new NotFoundException("OrderItem não encontrado");
		}

		return orderItem;

	}

	public OrderItem save(OrderItem orderItem) {

		return orderItemRepository.save(orderItem);

	}

	public OrderItem update(String uuid, OrderItem orderItem) {
 
		OrderItem currentOrderItem = this.getOrderItemByUuid(uuid);
 
		BeanUtils.copyProperties(orderItem, currentOrderItem, "id", "uuid");

		return orderItemRepository.save(currentOrderItem);

	}

	public boolean delete(String uuid) {

		OrderItem orderItem = this.getOrderItemByUuid(uuid);

		if (orderItem != null) {

			try {

				orderItemRepository.delete(orderItem);

				return true;

			} catch (EmptyResultDataAccessException ex) {

				System.out.println(ex.getMessage());

			}

		}

		return false;

	}

}
