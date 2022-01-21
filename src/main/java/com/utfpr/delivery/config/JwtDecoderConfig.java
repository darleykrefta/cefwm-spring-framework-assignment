package com.utfpr.delivery.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfig {

	@Bean
	public JwtDecoder jwtDecoder() {
		
		SecretKeySpec secretKey = new SecretKeySpec("32928e3a3710f3449af28f76a3488ca1e52fc5c9".getBytes(), "HmacSHA256");
		
		return NimbusJwtDecoder.withSecretKey(secretKey).build();
		
	}
	
}
