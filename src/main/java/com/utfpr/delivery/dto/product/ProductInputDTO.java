package com.utfpr.delivery.dto.product;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductInputDTO {

	@NotBlank
	private String name;

	@NotBlank
	private String price;

}
