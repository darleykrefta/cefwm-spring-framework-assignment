package com.utfpr.delivery.mapper.orderitem;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.orderitem.OrderItemResponseDTO;
import com.utfpr.delivery.entity.OrderItem;

@Component
public class OrderItemResponseOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public OrderItemResponseDTO mapearDTO(OrderItem orderItem) {
		
		OrderItemResponseDTO orderItemeResumoDTO = modelMapper.map(orderItem, OrderItemResponseDTO.class);
		
		return orderItemeResumoDTO;
		
	}
	
	public List<OrderItemResponseDTO> mapearLista(List<OrderItem> orderItems) {
		
		return orderItems.stream()
				.map(orderItem -> mapearDTO(orderItem))
				.collect(Collectors.toList());
	}
	
}
