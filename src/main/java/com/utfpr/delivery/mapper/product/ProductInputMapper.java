package com.utfpr.delivery.mapper.product;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.product.ProductInputDTO;
import com.utfpr.delivery.entity.Product;

@Component
public class ProductInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Product mapearEntity(ProductInputDTO productInputDTO) {
		
		Product product = modelMapper.map(productInputDTO, Product.class);
		
		return product;
		
	}
	
	public List<Product> mapearLista(List<ProductInputDTO> productInputDTOs) {
		
		return productInputDTOs.stream()
				.map(productInputDTO -> mapearEntity(productInputDTO))
				.collect(Collectors.toList());
		
	}
	
}
