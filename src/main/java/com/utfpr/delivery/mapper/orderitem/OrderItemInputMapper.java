package com.utfpr.delivery.mapper.orderitem;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.orderitem.OrderItemInputDTO;
import com.utfpr.delivery.entity.OrderItem;

@Component
public class OrderItemInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public OrderItem mapearEntity(OrderItemInputDTO orderItemInputDTO) {
		
		OrderItem orderItem = modelMapper.map(orderItemInputDTO, OrderItem.class);
		
		return orderItem;
		
	}
	
	public List<OrderItem> mapearLista(List<OrderItemInputDTO> orderItemInputDTOs) {
		
		return orderItemInputDTOs.stream()
				.map(orderItemInputDTO -> mapearEntity(orderItemInputDTO))
				.collect(Collectors.toList());
		
	}
	
}
