package com.utfpr.delivery.dto.orderitem;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderItemInputDTO {

	@NotBlank
	private String product;

	@NotBlank
	private Double quantity;

}
