package com.utfpr.delivery.dto.order;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderInputDTO {

	@NotBlank
	private String restaurant;

	@NotBlank
	private String client;

}
