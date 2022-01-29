package com.utfpr.delivery.dto.restaurant;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RestaurantInputDTO {
	
	@NotBlank
	private String name;

	@NotNull
	@DecimalMin(value = "0.00", inclusive = false)
	@Digits(integer = 15, fraction = 2)
	private BigDecimal taxaFrete;

}
