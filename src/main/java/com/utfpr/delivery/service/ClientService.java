package com.utfpr.delivery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Client;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.ClientRepository;
import com.utfpr.delivery.utils.Utils;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Client getClientByUuid(String uuid) {
		
		Client client = clientRepository.findByUuid(uuid);

		if (client == null) {
			throw new NotFoundException("Client não encontrado");
		}

		return client;

	}

	public Client save(Client client) {

		return clientRepository.save(client);

	}

	public Client update(String uuid, Client client) {
 
		Client clientAtual = this.getClientByUuid(uuid);
 
		BeanUtils.copyProperties(client, clientAtual, "id", "uuid");

		return clientRepository.save(clientAtual);

	}

	public Client change(String uuid, Map<String, Object> campos) {

		Client clientAtual = this.getClientByUuid(uuid);

		Utils.merge(clientAtual, campos);

		clientAtual = this.save(clientAtual);

		return clientAtual;

	}

	public boolean delete(String uuid) {

		Client client = this.getClientByUuid(uuid);

		if (client != null) {

			try {

				clientRepository.delete(client);

				return true;

			} catch (EmptyResultDataAccessException ex) {

				System.out.println(ex.getMessage());

			}

		}

		return false;

	}

}
