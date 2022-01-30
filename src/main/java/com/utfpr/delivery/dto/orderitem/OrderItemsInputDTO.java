package com.utfpr.delivery.dto.orderitem;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderItemsInputDTO {

	@NotNull
	private List<OrderItemInputDTO> items;

}
