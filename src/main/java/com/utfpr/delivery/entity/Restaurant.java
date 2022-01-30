package com.utfpr.delivery.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 45)
	private String uuid;
	
	@Column(length = 45)
	private String name;
	
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;
	
	@PrePersist
	private void gerarUUID() {
		setUuid(UUID.randomUUID().toString());
	}
}
