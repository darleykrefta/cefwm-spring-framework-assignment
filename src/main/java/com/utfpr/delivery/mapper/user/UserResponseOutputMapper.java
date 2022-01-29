package com.utfpr.delivery.mapper.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.user.UserResponseDTO;
import com.utfpr.delivery.entity.User;

@Component
public class UserResponseOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public UserResponseDTO mapearDTO(User user) {
		
		UserResponseDTO usereResumoDTO = modelMapper.map(user, UserResponseDTO.class);
		
		return usereResumoDTO;
		
	}
	
	public List<UserResponseDTO> mapearLista(List<User> users) {
		
		return users.stream()
				.map(user -> mapearDTO(user))
				.collect(Collectors.toList());
	}
	
}
