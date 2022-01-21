package com.utfpr.delivery.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.utfpr.delivery.entity.Usuario;

import lombok.Getter;

@Getter
public class AuthUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String uuid;
	
	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		
		super(usuario.getEmail(), usuario.getPassword(), authorities);
		
		System.out.println("AuthUser");
		System.out.println(usuario.getNome());
		
		this.nome = usuario.getNome();
		
		this.uuid = usuario.getUuid();
		
	}
	
}
