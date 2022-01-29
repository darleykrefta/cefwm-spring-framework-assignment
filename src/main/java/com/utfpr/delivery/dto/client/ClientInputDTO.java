package com.utfpr.delivery.dto.client;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClientInputDTO {

	@NotBlank
	private String name;

	@NotBlank
	private String phone;

	@NotBlank
	private String email;

}
