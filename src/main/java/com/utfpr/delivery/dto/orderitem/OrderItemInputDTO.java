package com.utfpr.delivery.dto.orderitem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderItemInputDTO {

	@NotBlank
	private String product;

	@NotNull
	private Double quantity;

}
