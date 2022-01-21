package com.utfpr.delivery.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.RestauranteDTO;
import com.utfpr.delivery.entity.Restaurante;

@Component
public class RestauranteOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public RestauranteDTO mapearDTO(Restaurante restaurante) {
		
		RestauranteDTO restauranteDTO = modelMapper.map(restaurante, RestauranteDTO.class);
		
		return restauranteDTO;
		
	}
	
	public List<RestauranteDTO> mapearLista(List<Restaurante> restaurantes) {
		
		return restaurantes.stream()
				.map(restaurante -> mapearDTO(restaurante))
				.collect(Collectors.toList());
		
	}
	
}
