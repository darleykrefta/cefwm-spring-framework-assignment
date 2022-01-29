package com.utfpr.delivery.mapper.order;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.order.OrderInputDTO;
import com.utfpr.delivery.entity.Order;

@Component
public class OrderInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Order mapearEntity(OrderInputDTO orderInputDTO) {
		
		Order order = modelMapper.map(orderInputDTO, Order.class);
		
		return order;
		
	}
	
	public List<Order> mapearLista(List<OrderInputDTO> orderInputDTOs) {
		
		return orderInputDTOs.stream()
				.map(orderInputDTO -> mapearEntity(orderInputDTO))
				.collect(Collectors.toList());
		
	}
	
}
