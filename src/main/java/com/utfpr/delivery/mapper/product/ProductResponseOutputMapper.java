package com.utfpr.delivery.mapper.product;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.product.ProductResponseDTO;
import com.utfpr.delivery.entity.Product;

@Component
public class ProductResponseOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public ProductResponseDTO mapearDTO(Product product) {
		
		ProductResponseDTO producteResumoDTO = modelMapper.map(product, ProductResponseDTO.class);
		
		return producteResumoDTO;
		
	}
	
	public List<ProductResponseDTO> mapearLista(List<Product> products) {
		
		return products.stream()
				.map(product -> mapearDTO(product))
				.collect(Collectors.toList());
	}
	
}
