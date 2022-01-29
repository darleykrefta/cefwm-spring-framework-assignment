package com.utfpr.delivery.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		System.out.println("enhance");
		
		DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
		
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		
		Map<String, Object> info = new HashMap<>();
		info.put("name", authUser.getName());
		info.put("uuid", authUser.getUuid());
		
		oAuth2AccessToken.setAdditionalInformation(info);
		
		return oAuth2AccessToken;
		
	}
	
}
