package com.utfpr.delivery.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utfpr.delivery.entity.Usuario;
import com.utfpr.delivery.service.UsuarioService;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("loadUserByUsername");
		System.out.println(username);
		
		Usuario usuario = usuarioService.getUsuarioByEmail(username);
		
		return new AuthUser(usuario, Collections.emptyList());
		
	}
	
}
