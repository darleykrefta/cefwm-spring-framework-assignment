package com.utfpr.delivery.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.RestauranteResumoDTO;
import com.utfpr.delivery.entity.Restaurante;

@Component
public class RestauranteResumoOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public RestauranteResumoDTO mapearDTO(Restaurante restaurante) {
		
		RestauranteResumoDTO restauranteResumoDTO = modelMapper.map(restaurante, RestauranteResumoDTO.class);
		
		return restauranteResumoDTO;
		
	}
	
	public List<RestauranteResumoDTO> mapearLista(List<Restaurante> restaurantes) {
		
		return restaurantes.stream()
				.map(restaurante -> mapearDTO(restaurante))
				.collect(Collectors.toList());
		
		// List<RestauranteResumoDTO> lista = new List<RestauranteResumoDTO>();
		// for (Restaurante restaurante : restaurantes) {
		//    lista.add(mapearDTO(restaurante));
		// }
		
	}
	
}
