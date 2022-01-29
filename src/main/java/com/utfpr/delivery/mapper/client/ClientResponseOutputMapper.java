package com.utfpr.delivery.mapper.client;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.client.ClientResponseDTO;
import com.utfpr.delivery.entity.Client;

@Component
public class ClientResponseOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public ClientResponseDTO mapearDTO(Client client) {
		
		ClientResponseDTO clienteResumoDTO = modelMapper.map(client, ClientResponseDTO.class);
		
		return clienteResumoDTO;
		
	}
	
	public List<ClientResponseDTO> mapearLista(List<Client> clients) {
		
		return clients.stream()
				.map(client -> mapearDTO(client))
				.collect(Collectors.toList());
	}
	
}
