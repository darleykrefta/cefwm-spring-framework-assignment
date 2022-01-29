package com.utfpr.delivery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Order;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.OrderRepository;
import com.utfpr.delivery.utils.Utils;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> listarTodosOsOrders() {

		return orderRepository.findAll();

	}

	public Order getOrderByUuid(String uuid) {

		Order order = orderRepository.findByUuid(uuid);

		if (order == null) {
			throw new NotFoundException("Order não encontrado");
		}

		return order;

	}

	public Order save(Order order) {

		return orderRepository.save(order);

	}

	public Order update(String uuid, Order order) {
 
		Order orderAtual = this.getOrderByUuid(uuid);
 
		BeanUtils.copyProperties(order, orderAtual, "id", "uuid");

		return orderRepository.save(orderAtual);

	}

	public Order change(String uuid, Map<String, Object> campos) {

		Order orderAtual = this.getOrderByUuid(uuid);

		Utils.merge(orderAtual, campos);

		orderAtual = this.save(orderAtual);

		return orderAtual;

	}

	public boolean delete(String uuid) {

		Order order = this.getOrderByUuid(uuid);

		if (order != null) {

			try {

				orderRepository.delete(order);

				return true;

			} catch (EmptyResultDataAccessException ex) {

				System.out.println(ex.getMessage());

			}

		}

		return false;

	}

}
