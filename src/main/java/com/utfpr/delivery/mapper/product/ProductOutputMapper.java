package com.utfpr.delivery.mapper.product;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.product.ProductDTO;
import com.utfpr.delivery.entity.Product;

@Component
public class ProductOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public ProductDTO mapearDTO(Product product) {
		
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		
		return productDTO;
		
	}
	
	public List<ProductDTO> mapearLista(List<Product> products) {
		
		return products.stream()
				.map(product -> mapearDTO(product))
				.collect(Collectors.toList());
		
	}
	
}
