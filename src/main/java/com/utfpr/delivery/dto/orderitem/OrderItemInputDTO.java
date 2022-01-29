package com.utfpr.delivery.dto.orderitem;

import javax.validation.constraints.NotBlank;

import com.utfpr.delivery.entity.Order;
import com.utfpr.delivery.entity.Product;

import lombok.Data;

@Data
public class OrderItemInputDTO {

	@NotBlank
	private String product;

	@NotBlank
	private Double quantity;

}
