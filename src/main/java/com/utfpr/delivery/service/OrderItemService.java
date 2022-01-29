package com.utfpr.delivery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.OrderItem;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.OrderItemRepository;
import com.utfpr.delivery.utils.Utils;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	public List<OrderItem> listarTodosOsOrderItems() {

		return orderItemRepository.findAll();

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
 
		OrderItem orderItemAtual = this.getOrderItemByUuid(uuid);
 
		BeanUtils.copyProperties(orderItem, orderItemAtual, "id", "uuid");

		return orderItemRepository.save(orderItemAtual);

	}

	public OrderItem change(String uuid, Map<String, Object> campos) {

		OrderItem orderItemAtual = this.getOrderItemByUuid(uuid);

		Utils.merge(orderItemAtual, campos);

		orderItemAtual = this.save(orderItemAtual);

		return orderItemAtual;

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
