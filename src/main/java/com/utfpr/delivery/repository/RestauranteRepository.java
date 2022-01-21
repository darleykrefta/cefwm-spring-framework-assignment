package com.utfpr.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.delivery.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	public Restaurante findByUuid(String uuid);
	
}
