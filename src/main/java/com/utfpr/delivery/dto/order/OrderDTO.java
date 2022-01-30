package com.utfpr.delivery.dto.order;

import com.utfpr.delivery.entity.Client;
import com.utfpr.delivery.entity.Restaurant;

import lombok.Data;

@Data
public class OrderDTO {

	private String uuid;

	private Restaurant restaurant;

	private Client client;

}
