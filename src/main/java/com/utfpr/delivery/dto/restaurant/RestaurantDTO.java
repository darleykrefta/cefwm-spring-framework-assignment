package com.utfpr.delivery.dto.restaurant;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestaurantDTO {

	private String uuid;
	
	private String name;
	
	private BigDecimal freight;

}
