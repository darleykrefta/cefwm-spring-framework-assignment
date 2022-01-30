
package com.utfpr.delivery.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.delivery.dto.client.ClientDTO;
import com.utfpr.delivery.dto.client.ClientInputDTO;
import com.utfpr.delivery.dto.client.ClientResponseDTO;
import com.utfpr.delivery.entity.Client;
import com.utfpr.delivery.mapper.client.ClientInputMapper;
import com.utfpr.delivery.mapper.client.ClientOutputMapper;
import com.utfpr.delivery.mapper.client.ClientResponseOutputMapper;
import com.utfpr.delivery.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientResponseOutputMapper clientResponseOutputMapper;
	
	@Autowired
	private ClientOutputMapper clientOutputMapper;
	
	@Autowired
	private ClientInputMapper clientInputMapper;
	
	@GetMapping
	@ResponseBody
	public List<ClientResponseDTO> listarTodosOsClients() {
		
		List<Client> clients = clientService.getAllClients();
		
		List<ClientResponseDTO> clientResponseDTOs = clientResponseOutputMapper.mapearLista(clients);
		
		return clientResponseDTOs;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseBody
	public ClientDTO getClientById(@PathVariable String uuid) {
		
		Client client = clientService.getClientByUuid(uuid);
		
		ClientDTO clientDTO = clientOutputMapper.mapearDTO(client);
		
		return clientDTO;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ClientDTO adicionar(@RequestBody @Valid ClientInputDTO clientInputDTO) {
		
		Client client = clientInputMapper.mapearEntity(clientInputDTO);
		
		client = clientService.save(client);
		
		ClientDTO clientDTO = clientOutputMapper.mapearDTO(client);
		
		return clientDTO;
		
	}
	
	@PutMapping("/{uuid}")
	@ResponseBody
	private ClientDTO alterar(@PathVariable String uuid, @Valid @RequestBody ClientInputDTO clientInputDTO) {
		
		Client client = clientInputMapper.mapearEntity(clientInputDTO);
		
		client = clientService.update(uuid, client);
		
		ClientDTO clientDTO = clientOutputMapper.mapearDTO(client);
		
		return clientDTO;
		
	}
	
	@DeleteMapping("/{uuid}")
	private ResponseEntity<Client> deletar(@PathVariable String uuid) {
		
		if (clientService.delete(uuid)) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}

}
