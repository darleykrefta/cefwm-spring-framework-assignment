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

import com.utfpr.delivery.dto.product.ProductDTO;
import com.utfpr.delivery.dto.product.ProductInputDTO;
import com.utfpr.delivery.dto.product.ProductResponseDTO;
import com.utfpr.delivery.entity.Product;
import com.utfpr.delivery.mapper.product.ProductInputMapper;
import com.utfpr.delivery.mapper.product.ProductOutputMapper;
import com.utfpr.delivery.mapper.product.ProductResponseOutputMapper;
import com.utfpr.delivery.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductResponseOutputMapper productResponseOutputMapper;
	
	@Autowired
	private ProductOutputMapper productOutputMapper;
	
	@Autowired
	private ProductInputMapper productInputMapper;
	
	@GetMapping
	@ResponseBody
	public List<ProductResponseDTO> listarTodosOsProducts() {
		
		List<Product> products = productService.listarTodosOsProducts();
		
		List<ProductResponseDTO> productResponseDTOs = productResponseOutputMapper.mapearLista(products);
		
		return productResponseDTOs;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseBody
	public ProductDTO getProductById(@PathVariable String uuid) {
		
		Product product = productService.getProductByUuid(uuid);
		
		ProductDTO productDTO = productOutputMapper.mapearDTO(product);
		
		return productDTO;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ProductDTO adicionar(@RequestBody @Valid ProductInputDTO productInputDTO) {
		
		Product product = productInputMapper.mapearEntity(productInputDTO);
		
		product = productService.save(product);
		
		ProductDTO productDTO = productOutputMapper.mapearDTO(product);
		
		return productDTO;
		
	}
	
	@PutMapping("/{uuid}")
	@ResponseBody
	private ProductDTO alterar(@PathVariable String uuid, @Valid @RequestBody ProductInputDTO productInputDTO) {
		
		Product product = productInputMapper.mapearEntity(productInputDTO);
		
		product = productService.update(uuid, product);
		
		ProductDTO productDTO = productOutputMapper.mapearDTO(product);
		
		return productDTO;
		
	}
	
	@DeleteMapping("/{uuid}")
	private ResponseEntity<Product> deletar(@PathVariable String uuid) {
		
		if (productService.delete(uuid)) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}

}
