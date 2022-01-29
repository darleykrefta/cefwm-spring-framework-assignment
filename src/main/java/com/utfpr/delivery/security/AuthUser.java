package com.utfpr.delivery.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class AuthUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String uuid;
	
	public AuthUser(com.utfpr.delivery.entity.User user, Collection<? extends GrantedAuthority> authorities) {
		
		super(user.getEmail(), user.getPassword(), authorities);
		
		System.out.println("AuthUser");
		System.out.println(user.getName());
		
		this.name = user.getName();
		
		this.uuid = user.getUuid();
		
	}
	
}
