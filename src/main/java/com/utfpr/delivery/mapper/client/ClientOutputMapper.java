package com.utfpr.delivery.mapper.client;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.client.ClientDTO;
import com.utfpr.delivery.entity.Client;

@Component
public class ClientOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public ClientDTO mapearDTO(Client client) {
		
		ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
		
		return clientDTO;
		
	}
	
	public List<ClientDTO> mapearLista(List<Client> clients) {
		
		return clients.stream()
				.map(client -> mapearDTO(client))
				.collect(Collectors.toList());
		
	}
	
}
