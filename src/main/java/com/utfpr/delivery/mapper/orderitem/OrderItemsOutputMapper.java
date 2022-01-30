package com.utfpr.delivery.mapper.orderitem;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.orderitem.OrderItemDTO;
import com.utfpr.delivery.entity.OrderItem;

@Component
public class OrderItemsOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public List<OrderItemDTO> mapearDTO(List<OrderItem> orderItems) {
		
		List<OrderItemDTO> orderItemsOutput =  new ArrayList<OrderItemDTO>();
		
		for (final OrderItem item : orderItems) {
			OrderItemDTO orderItemDTO = modelMapper.map(item, OrderItemDTO.class);
			orderItemsOutput.add(orderItemDTO);
		}
		
		return orderItemsOutput;
		
	} 
	
}
