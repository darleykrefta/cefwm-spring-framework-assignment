package com.utfpr.delivery.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.RestauranteInputDTO;
import com.utfpr.delivery.entity.Restaurante;

@Component
public class RestauranteInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Restaurante mapearEntity(RestauranteInputDTO restauranteInputDTO) {
		
		Restaurante restaurante = modelMapper.map(restauranteInputDTO, Restaurante.class);
		
		return restaurante;
		
	}
	
	public List<Restaurante> mapearLista(List<RestauranteInputDTO> restauranteInputDTOs) {
		
		return restauranteInputDTOs.stream()
				.map(restauranteInputDTO -> mapearEntity(restauranteInputDTO))
				.collect(Collectors.toList());
		
	}
	
}
