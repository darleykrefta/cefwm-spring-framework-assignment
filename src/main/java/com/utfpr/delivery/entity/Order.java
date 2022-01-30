package com.utfpr.delivery.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", length = 45)
	private String uuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant")
	private Restaurant restaurant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;

	@PrePersist
	private void gerarUUID() {
		setUuid(UUID.randomUUID().toString());
	}
}
