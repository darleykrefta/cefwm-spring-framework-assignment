package com.utfpr.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class JwtAccessTokenConverterConfig {

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		
		JwtAccessTokenConverter jwt = new JwtAccessTokenConverter();
		
		jwt.setSigningKey("32928e3a3710f3449af28f76a3488ca1e52fc5c9");
		
		return jwt;
		
	}
	
}
