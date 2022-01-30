package com.utfpr.delivery.entity;
 

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
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "uuid", length = 36)
	private String uuid;
	
	@Column(length = 45, nullable = false)
	private String name;
	
	@Column(length = 20)
	private String phone;
	
	@Column(length = 100)
	private String email;
	
	@PrePersist
	private void gerarUUID() {
		setUuid(UUID.randomUUID().toString());
	}
}
