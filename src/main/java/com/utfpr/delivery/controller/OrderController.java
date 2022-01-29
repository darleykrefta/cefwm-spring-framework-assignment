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

import com.utfpr.delivery.dto.order.OrderDTO;
import com.utfpr.delivery.dto.order.OrderInputDTO;
import com.utfpr.delivery.dto.order.OrderResponseDTO;
import com.utfpr.delivery.entity.Order;
import com.utfpr.delivery.mapper.order.OrderInputMapper;
import com.utfpr.delivery.mapper.order.OrderOutputMapper;
import com.utfpr.delivery.mapper.order.OrderResponseOutputMapper;
import com.utfpr.delivery.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderResponseOutputMapper orderResponseOutputMapper;
	
	@Autowired
	private OrderOutputMapper orderOutputMapper;
	
	@Autowired
	private OrderInputMapper orderInputMapper;
	
	@GetMapping
	@ResponseBody
	public List<OrderResponseDTO> listarTodosOsOrders() {
		
		List<Order> orders = orderService.listarTodosOsOrders();
		
		List<OrderResponseDTO> orderResponseDTOs = orderResponseOutputMapper.mapearLista(orders);
		
		return orderResponseDTOs;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseBody
	public OrderDTO getOrderById(@PathVariable String uuid) {
		
		Order order = orderService.getOrderByUuid(uuid);
		
		OrderDTO orderDTO = orderOutputMapper.mapearDTO(order);
		
		return orderDTO;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private OrderDTO adicionar(@RequestBody @Valid OrderInputDTO orderInputDTO) {
		
		Order order = orderInputMapper.mapearEntity(orderInputDTO);
		
		order = orderService.save(order);
		
		OrderDTO orderDTO = orderOutputMapper.mapearDTO(order);
		
		return orderDTO;
		
	}
	
	@PutMapping("/{uuid}")
	@ResponseBody
	private OrderDTO alterar(@PathVariable String uuid, @Valid @RequestBody OrderInputDTO orderInputDTO) {
		
		Order order = orderInputMapper.mapearEntity(orderInputDTO);
		
		order = orderService.update(uuid, order);
		
		OrderDTO orderDTO = orderOutputMapper.mapearDTO(order);
		
		return orderDTO;
		
	}
	
	@PatchMapping("/{uuid}")
	@ResponseBody
	private Order ajustar(@PathVariable String uuid, @RequestBody OrderInputDTO orderInputDTO) {
		
		Order order = orderInputMapper.mapearEntity(orderInputDTO);
		
		return orderService.update(uuid, order);
		
	}
	
	@DeleteMapping("/{uuid}")
	private ResponseEntity<Order> deletar(@PathVariable String uuid) {
		
		if (orderService.delete(uuid)) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}

}
