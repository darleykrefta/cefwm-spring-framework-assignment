package com.utfpr.delivery.dto.order;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.utfpr.delivery.dto.orderitem.OrderItemInputDTO;

import lombok.Data;

@Data
public class OrderInputDTO {

	@NotBlank
	private String restaurant;

	@NotBlank
	private String client;

	@NotBlank
	private String user;

	private List<OrderItemInputDTO> items;

}
