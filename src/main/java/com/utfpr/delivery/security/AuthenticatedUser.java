package com.utfpr.delivery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.entity.User; 
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.UserRepository;

@Component
public class AuthenticatedUser {
	
	@Autowired
	UserRepository userRepository;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public User getUser() {
		
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		User user = userRepository.findByUuid(jwt.getClaim("uuid"));
		
		if (user == null) {
			throw new NotFoundException("Usuário inválido");
		}
		
		return user;
		
	}
	
}

