package com.utfpr.delivery.mapper.restaurant;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.restaurant.RestaurantDTO;
import com.utfpr.delivery.entity.Restaurant;

@Component
public class RestaurantOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public RestaurantDTO mapearDTO(Restaurant restaurant) {
		
		RestaurantDTO restaurantDTO = modelMapper.map(restaurant, RestaurantDTO.class);
		
		return restaurantDTO;
		
	}
	
	public List<RestaurantDTO> mapearLista(List<Restaurant> restaurants) {
		
		return restaurants.stream()
				.map(restaurant -> mapearDTO(restaurant))
				.collect(Collectors.toList());
		
	}
	
}
