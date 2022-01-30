package com.utfpr.delivery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.delivery.dto.orderitem.OrderItemDTO;
import com.utfpr.delivery.dto.orderitem.OrderItemInputDTO;
import com.utfpr.delivery.entity.OrderItem;
import com.utfpr.delivery.entity.Product;
import com.utfpr.delivery.entity.Restaurant;
import com.utfpr.delivery.mapper.orderitem.OrderItemInputMapper;
import com.utfpr.delivery.mapper.orderitem.OrderItemOutputMapper;
import com.utfpr.delivery.mapper.orderitem.OrderItemResponseOutputMapper;
import com.utfpr.delivery.service.OrderItemService;
import com.utfpr.delivery.service.ProductService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemResponseOutputMapper orderItemResponseOutputMapper;
	
	@Autowired
	private OrderItemOutputMapper orderItemOutputMapper;
	
	@Autowired
	private OrderItemInputMapper orderItemInputMapper;
	
	
	@GetMapping("/{uuid}")
	@ResponseBody
	public OrderItemDTO getOrderItemById(@PathVariable String uuid) {
		
		OrderItem orderItem = orderItemService.getOrderItemByUuid(uuid);
		
		OrderItemDTO orderItemDTO = orderItemOutputMapper.mapearDTO(orderItem);
		
		return orderItemDTO;
		
	}
	 
	@PutMapping("/{uuid}")
	@ResponseBody
	private OrderItemDTO alterar(@PathVariable String uuid, @Valid @RequestBody OrderItemInputDTO orderItemInputDTO) {
		
		OrderItem orderItem = orderItemInputMapper.mapearEntity(orderItemInputDTO);
		
		Product product = productService.getProductByUuid(orderItemInputDTO.getProduct());
		orderItem.setProduct(product);
		
		orderItem = orderItemService.update(uuid, orderItem);
		
		OrderItemDTO orderItemDTO = orderItemOutputMapper.mapearDTO(orderItem);
		
		return orderItemDTO;
		
	}
	
	@PatchMapping("/{uuid}")
	@ResponseBody
	private OrderItem ajustar(@PathVariable String uuid, @RequestBody OrderItemInputDTO orderItemInputDTO) {
		
		OrderItem orderItem = orderItemInputMapper.mapearEntity(orderItemInputDTO);
		
		return orderItemService.update(uuid, orderItem);
		
	}
	
	@DeleteMapping("/{uuid}")
	private ResponseEntity<OrderItem> deletar(@PathVariable String uuid) {
		
		if (orderItemService.delete(uuid)) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}

}
