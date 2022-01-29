package com.utfpr.delivery.mapper.order;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.order.OrderDTO;
import com.utfpr.delivery.entity.Order;

@Component
public class OrderOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public OrderDTO mapearDTO(Order order) {
		
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		
		return orderDTO;
		
	}
	
	public List<OrderDTO> mapearLista(List<Order> orders) {
		
		return orders.stream()
				.map(order -> mapearDTO(order))
				.collect(Collectors.toList());
		
	}
	
}
