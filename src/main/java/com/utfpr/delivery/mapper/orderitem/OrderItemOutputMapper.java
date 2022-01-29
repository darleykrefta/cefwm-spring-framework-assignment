package com.utfpr.delivery.mapper.orderitem;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.orderitem.OrderItemDTO;
import com.utfpr.delivery.entity.OrderItem;

@Component
public class OrderItemOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public OrderItemDTO mapearDTO(OrderItem orderItem) {
		
		OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);
		
		return orderItemDTO;
		
	}
	
	public List<OrderItemDTO> mapearLista(List<OrderItem> orderItems) {
		
		return orderItems.stream()
				.map(orderItem -> mapearDTO(orderItem))
				.collect(Collectors.toList());
		
	}
	
}
