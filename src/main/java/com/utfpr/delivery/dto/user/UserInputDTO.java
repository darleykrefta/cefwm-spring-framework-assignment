package com.utfpr.delivery.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserInputDTO {

	@NotBlank
	private String name;

	@NotBlank
	private String username;

	@NotBlank
	private String email;

	@NotBlank
	private String status;

	@NotBlank
	private String restaurant;

}
