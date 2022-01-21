package com.utfpr.delivery.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Restaurante;
import com.utfpr.delivery.entity.Usuario;
import com.utfpr.delivery.exception.BadRequestException;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.RestauranteRepository;
import com.utfpr.delivery.security.AuthenticatedUser;
import com.utfpr.delivery.utils.Utils;

@Service
public class RestauranteService {
	
	@Autowired
	private AuthenticatedUser authenticatedUser;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listarTodosOsRestaurantes() {
		
		return restauranteRepository.findAll();
		
	}
	
	public Restaurante getRestauranteByUuid(String uuid) {
		
		Restaurante restaurante = restauranteRepository.findByUuid(uuid);
		
		if (restaurante == null) {
			throw new NotFoundException("Restaurante não encontrado");
		}
		
		return restaurante;
		
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		
		return restauranteRepository.save(restaurante);
		
	}
	
	public Restaurante alterar(String uuid, Restaurante restaurante) {
		
		Usuario usuario = authenticatedUser.getUsuario();
		
		Restaurante restauranteAtual = this.getRestauranteByUuid(uuid);
		
		if (!restauranteAtual.getId().equals(usuario.getRestaurante().getId())) {
			throw new BadRequestException("Usuário não tem acesso para modificar este restaurante");
		}
		
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "uuid");
		
		return restauranteRepository.save(restauranteAtual);
		
	}
	
	public Restaurante ajustar(String uuid, Map<String, Object> campos) {
		
		Usuario usuario = authenticatedUser.getUsuario();
		
		Restaurante restauranteAtual = this.getRestauranteByUuid(uuid);
		
		if (!restauranteAtual.getId().equals(usuario.getRestaurante().getId())) {
			throw new BadRequestException("Usuário não tem acesso para modificar este restaurante");
		}
		
		Utils.merge(restauranteAtual, campos);
		
		restauranteAtual = this.salvar(restauranteAtual);
		
		return restauranteAtual;
		
	}
	
	public boolean excluir(String uuid) {
		
		Usuario usuario = authenticatedUser.getUsuario();
		
		Restaurante restaurante = this.getRestauranteByUuid(uuid);
		
		if (!restaurante.getId().equals(usuario.getRestaurante().getId())) {
			throw new BadRequestException("Usuário não tem acesso para excluir este restaurante");
		}
		
		if (restaurante != null) {
			
			try {
		
				restauranteRepository.delete(restaurante);
				
				return true;
				
			} catch (EmptyResultDataAccessException ex) {
				
				System.out.println(ex.getMessage());
				
			}
			
		}
		
		return false;
		
	}
	
}
