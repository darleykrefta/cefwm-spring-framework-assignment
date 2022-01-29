package com.utfpr.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.delivery.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	public Client findByUuid(String uuid);
	
}
