package com.utfpr.delivery.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Product;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> listarTodosOsProducts() {

		return productRepository.findAll();

	}

	public Product getProductByUuid(String uuid) {

		Product product = productRepository.findByUuid(uuid);

		if (product == null) {
			throw new NotFoundException("Product não encontrado");
		}

		return product;

	}

	public Product save(Product product) {

		return productRepository.save(product);

	}

	public Product update(String uuid, Product product) {
 
		Product productAtual = this.getProductByUuid(uuid);
 
		BeanUtils.copyProperties(product, productAtual, "id", "uuid");

		return productRepository.save(productAtual);

	}

	public boolean delete(String uuid) {

		Product product = this.getProductByUuid(uuid);

		if (product != null) {

			try {

				productRepository.delete(product);

				return true;

			} catch (EmptyResultDataAccessException ex) {

				System.out.println(ex.getMessage());

			}

		}

		return false;

	}

}
