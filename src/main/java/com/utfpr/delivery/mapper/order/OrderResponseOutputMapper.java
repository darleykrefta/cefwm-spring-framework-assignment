package com.utfpr.delivery.mapper.order;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.order.OrderResponseDTO;
import com.utfpr.delivery.entity.Order;

@Component
public class OrderResponseOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public OrderResponseDTO mapearDTO(Order order) {
		
		OrderResponseDTO ordereResumoDTO = modelMapper.map(order, OrderResponseDTO.class);
		
		return ordereResumoDTO;
		
	}
	
	public List<OrderResponseDTO> mapearLista(List<Order> orders) {
		
		return orders.stream()
				.map(order -> mapearDTO(order))
				.collect(Collectors.toList());
	}
	
}
