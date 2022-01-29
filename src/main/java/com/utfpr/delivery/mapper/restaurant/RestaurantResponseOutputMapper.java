package com.utfpr.delivery.mapper.restaurant;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.restaurant.RestaurantResponseDTO;
import com.utfpr.delivery.entity.Restaurant;

@Component
public class RestaurantResponseOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public RestaurantResponseDTO mapearDTO(Restaurant restaurant) {
		
		RestaurantResponseDTO restauranteResumoDTO = modelMapper.map(restaurant, RestaurantResponseDTO.class);
		
		return restauranteResumoDTO;
		
	}
	
	public List<RestaurantResponseDTO> mapearLista(List<Restaurant> restaurants) {
		
		return restaurants.stream()
				.map(restaurant -> mapearDTO(restaurant))
				.collect(Collectors.toList());
	}
	
}
