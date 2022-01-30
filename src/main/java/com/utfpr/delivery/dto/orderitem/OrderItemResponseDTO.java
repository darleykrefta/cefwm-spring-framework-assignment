package com.utfpr.delivery.dto.orderitem;

import com.utfpr.delivery.entity.Product;

import lombok.Data;

@Data
public class OrderItemResponseDTO {

	private String uuid;

	private Double quantity;
	
	private Double total;

	private Product product;
}
