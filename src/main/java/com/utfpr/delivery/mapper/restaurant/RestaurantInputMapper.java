package com.utfpr.delivery.mapper.restaurant;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.restaurant.RestaurantInputDTO;
import com.utfpr.delivery.entity.Restaurant;

@Component
public class RestaurantInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Restaurant mapearEntity(RestaurantInputDTO restauranteInputDTO) {
		
		Restaurant restaurante = modelMapper.map(restauranteInputDTO, Restaurant.class);
		
		return restaurante;
		
	}
	
	public List<Restaurant> mapearLista(List<RestaurantInputDTO> restauranteInputDTOs) {
		
		return restauranteInputDTOs.stream()
				.map(restauranteInputDTO -> mapearEntity(restauranteInputDTO))
				.collect(Collectors.toList());
		
	}
	
}
