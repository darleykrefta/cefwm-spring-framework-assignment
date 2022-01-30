package com.utfpr.delivery.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import com.utfpr.delivery.dto.order.OrderInputItemsDTO;
import com.utfpr.delivery.dto.order.OrderResponseDTO;
import com.utfpr.delivery.dto.orderitem.OrderItemDTO;
import com.utfpr.delivery.dto.orderitem.OrderItemInputDTO;
import com.utfpr.delivery.dto.orderitem.OrderItemResponseDTO;
import com.utfpr.delivery.dto.orderitem.OrderItemsInputDTO;
import com.utfpr.delivery.entity.Client;
import com.utfpr.delivery.entity.Order;
import com.utfpr.delivery.entity.OrderItem;
import com.utfpr.delivery.entity.Product;
import com.utfpr.delivery.entity.Restaurant;
import com.utfpr.delivery.entity.User;
import com.utfpr.delivery.mapper.order.OrderInputMapper;
import com.utfpr.delivery.mapper.order.OrderOutputMapper;
import com.utfpr.delivery.mapper.order.OrderResponseOutputMapper;
import com.utfpr.delivery.mapper.orderitem.OrderItemInputMapper;
import com.utfpr.delivery.mapper.orderitem.OrderItemOutputMapper;
import com.utfpr.delivery.mapper.orderitem.OrderItemResponseOutputMapper;
import com.utfpr.delivery.mapper.orderitem.OrderItemsOutputMapper;
import com.utfpr.delivery.security.AuthenticatedUser;
import com.utfpr.delivery.service.ClientService;
import com.utfpr.delivery.service.OrderItemService;
import com.utfpr.delivery.service.OrderService;
import com.utfpr.delivery.service.ProductService;
import com.utfpr.delivery.service.RestaurantService;
import com.utfpr.delivery.service.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private AuthenticatedUser authenticatedUser;

	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderResponseOutputMapper orderResponseOutputMapper;

	@Autowired
	private OrderItemResponseOutputMapper orderItemResponseOutputMapper;

	@Autowired
	private OrderOutputMapper orderOutputMapper;

	@Autowired
	private OrderInputMapper orderInputMapper;

	@Autowired
	private OrderItemInputMapper orderItemInputMapper;
	
	@Autowired
	private OrderItemsOutputMapper orderItemsOutputMapper;

	@GetMapping
	@ResponseBody
	public List<OrderResponseDTO> getAllOrders() {

		List<Order> orders = orderService.getAllOrders();

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

	@GetMapping("/{orderId}/items")
	@ResponseBody
	public List<OrderItemResponseDTO> getAllOrderItemsByOrder(@PathVariable(value = "orderId") String orderId) {

		List<OrderItem> orderItems = orderItemService.getAllItemsByOrder(orderId);

		List<OrderItemResponseDTO> orderItemResponseDTOs = orderItemResponseOutputMapper.mapearLista(orderItems);

		return orderItemResponseDTOs;

	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private OrderDTO adicionar(@RequestBody @Valid OrderInputDTO orderInputDTO) {

		Order order = orderInputMapper.mapearEntity(orderInputDTO);

		Restaurant restaurant = restaurantService.getRestaurantByUuid(orderInputDTO.getRestaurant());
		order.setRestaurant(restaurant);

		Client client = clientService.getClientByUuid(orderInputDTO.getClient());
		order.setClient(client);

		User user = authenticatedUser.getUser();
		order.setUser(user);

		order = orderService.save(order);

		OrderDTO orderDTO = orderOutputMapper.mapearDTO(order);

		return orderDTO;

	}

	@PostMapping("/{orderId}/items")
	@ResponseStatus(code = HttpStatus.CREATED)
	private List<OrderItemDTO> createItemsByOrder(@RequestBody @Valid OrderItemsInputDTO orderItemsInputDTO, @PathVariable(value = "orderId") String orderId) {

		Order order = orderService.getOrderByUuid(orderId);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		for (final OrderItemInputDTO item : orderItemsInputDTO.getItems()) {

			OrderItem orderItem = orderItemInputMapper.mapearEntity(item);
			
			Product product = productService.getProductByUuid(item.getProduct());
			orderItem.setProduct(product);
			orderItem.setOrder(order);
			orderItem.setQuantity(item.getQuantity());
			
			orderItem = orderItemService.save(orderItem);
	        
			orderItems.add(orderItem);
		}
		 
		List<OrderItemDTO> orderItemDTO = orderItemsOutputMapper.mapearDTO(orderItems);

		return orderItemDTO;

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
