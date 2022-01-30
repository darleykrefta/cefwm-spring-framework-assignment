package com.utfpr.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.delivery.dto.orderitem.OrderItemDTO;
import com.utfpr.delivery.entity.OrderItem;
import com.utfpr.delivery.mapper.orderitem.OrderItemOutputMapper;
import com.utfpr.delivery.service.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	
	@Autowired
	private OrderItemOutputMapper orderItemOutputMapper;
	
	
	@GetMapping("/{uuid}")
	@ResponseBody
	public OrderItemDTO getOrderItemById(@PathVariable String uuid) {
		
		OrderItem orderItem = orderItemService.getOrderItemByUuid(uuid);
		
		OrderItemDTO orderItemDTO = orderItemOutputMapper.mapearDTO(orderItem);
		
		return orderItemDTO;
		
	}
	
	@DeleteMapping("/{uuid}")
	private ResponseEntity<OrderItem> delete(@PathVariable String uuid) {
		
		if (orderItemService.delete(uuid)) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}

}
