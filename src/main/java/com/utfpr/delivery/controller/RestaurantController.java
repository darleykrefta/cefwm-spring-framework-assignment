package com.utfpr.delivery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.delivery.dto.restaurant.RestaurantDTO;
import com.utfpr.delivery.dto.restaurant.RestaurantInputDTO;
import com.utfpr.delivery.dto.restaurant.RestaurantResponseDTO;
import com.utfpr.delivery.entity.Restaurant;
import com.utfpr.delivery.mapper.restaurant.RestaurantInputMapper;
import com.utfpr.delivery.mapper.restaurant.RestaurantOutputMapper;
import com.utfpr.delivery.mapper.restaurant.RestaurantResponseOutputMapper;
import com.utfpr.delivery.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private RestaurantResponseOutputMapper restaurantResponseOutputMapper;
	
	@Autowired
	private RestaurantOutputMapper restaurantOutputMapper;
	
	@Autowired
	private RestaurantInputMapper restaurantInputMapper;
	
	@GetMapping
	@ResponseBody
	public List<RestaurantResponseDTO> listarTodosOsRestaurants() {
		
		List<Restaurant> restaurants = restaurantService.listarTodosOsRestaurants();
		
		List<RestaurantResponseDTO> restaurantResponseDTOs = restaurantResponseOutputMapper.mapearLista(restaurants);
		
		return restaurantResponseDTOs;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseBody
	public RestaurantDTO getRestaurantById(@PathVariable String uuid) {
		
		Restaurant restaurant = restaurantService.getRestaurantByUuid(uuid);
		
		RestaurantDTO restaurantDTO = restaurantOutputMapper.mapearDTO(restaurant);
		
		return restaurantDTO;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private RestaurantDTO adicionar(@RequestBody @Valid RestaurantInputDTO restaurantInputDTO) {
		
		Restaurant restaurant = restaurantInputMapper.mapearEntity(restaurantInputDTO);
		
		restaurant = restaurantService.save(restaurant);
		
		RestaurantDTO restaurantDTO = restaurantOutputMapper.mapearDTO(restaurant);
		
		return restaurantDTO;
		
	}
	
	@PutMapping("/{uuid}")
	@ResponseBody
	private RestaurantDTO alterar(@PathVariable String uuid, @Valid @RequestBody RestaurantInputDTO restaurantInputDTO) {
		
		Restaurant restaurant = restaurantInputMapper.mapearEntity(restaurantInputDTO);
		
		restaurant = restaurantService.update(uuid, restaurant);
		
		RestaurantDTO restaurantDTO = restaurantOutputMapper.mapearDTO(restaurant);
		
		return restaurantDTO;
		
	}

	@DeleteMapping("/{uuid}")
	private ResponseEntity<Restaurant> deletar(@PathVariable String uuid) {
		
		if (restaurantService.delete(uuid)) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}

}
