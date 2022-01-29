package com.utfpr.delivery.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.Restaurant;
import com.utfpr.delivery.entity.User;
import com.utfpr.delivery.exception.BadRequestException;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.RestaurantRepository;
import com.utfpr.delivery.security.AuthenticatedUser;
import com.utfpr.delivery.utils.Utils;

@Service
public class RestaurantService {
	
	@Autowired
	private AuthenticatedUser authenticatedUser;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> listarTodosOsRestaurants() {
		
		return restaurantRepository.findAll();
		
	}
	
	public Restaurant getRestaurantByUuid(String uuid) {
		
		Restaurant restaurant = restaurantRepository.findByUuid(uuid);
		
		if (restaurant == null) {
			throw new NotFoundException("Restaurant não encontrado");
		}
		
		return restaurant;
		
	}
	
	public Restaurant save(Restaurant restaurant) {
		
		return restaurantRepository.save(restaurant);
		
	}
	
	public Restaurant update(String uuid, Restaurant restaurant) {
		
		User usuario = authenticatedUser.getUser();
		
		Restaurant restaurantAtual = this.getRestaurantByUuid(uuid);
		
		if (!restaurantAtual.getId().equals(usuario.getRestaurant().getId())) {
			throw new BadRequestException("Usuário não tem acesso para modificar este restaurant");
		}
		
		BeanUtils.copyProperties(restaurant, restaurantAtual, "id", "uuid");
		
		return restaurantRepository.save(restaurantAtual);
		
	}
	
	public Restaurant change(String uuid, Map<String, Object> campos) {
		
		User usuario = authenticatedUser.getUser();
		
		Restaurant restaurantAtual = this.getRestaurantByUuid(uuid);
		
		if (!restaurantAtual.getId().equals(usuario.getRestaurant().getId())) {
			throw new BadRequestException("Usuário não tem acesso para modificar este restaurant");
		}
		
		Utils.merge(restaurantAtual, campos);
		
		restaurantAtual = this.save(restaurantAtual);
		
		return restaurantAtual;
		
	}
	
	public boolean delete(String uuid) {
		
		User usuario = authenticatedUser.getUser();
		
		Restaurant restaurant = this.getRestaurantByUuid(uuid);
		
		if (!restaurant.getId().equals(usuario.getRestaurant().getId())) {
			throw new BadRequestException("Usuário não tem acesso para excluir este restaurant");
		}
		
		if (restaurant != null) {
			
			try {
		
				restaurantRepository.delete(restaurant);
				
				return true;
				
			} catch (EmptyResultDataAccessException ex) {
				
				System.out.println(ex.getMessage());
				
			}
			
		}
		
		return false;
		
	}
	
}
