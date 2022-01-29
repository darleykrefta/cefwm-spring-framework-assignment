package com.utfpr.delivery.mapper.client;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.client.ClientInputDTO;
import com.utfpr.delivery.entity.Client;

@Component
public class ClientInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Client mapearEntity(ClientInputDTO clienteInputDTO) {
		
		Client cliente = modelMapper.map(clienteInputDTO, Client.class);
		
		return cliente;
		
	}
	
	public List<Client> mapearLista(List<ClientInputDTO> clienteInputDTOs) {
		
		return clienteInputDTOs.stream()
				.map(clienteInputDTO -> mapearEntity(clienteInputDTO))
				.collect(Collectors.toList());
		
	}
	
}
