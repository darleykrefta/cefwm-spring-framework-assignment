package com.utfpr.delivery.mapper.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.user.UserDTO;
import com.utfpr.delivery.entity.User;

@Component
public class UserOutputMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	public UserDTO mapearDTO(User user) {
		
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		return userDTO;
		
	}
	
	public List<UserDTO> mapearLista(List<User> users) {
		
		return users.stream()
				.map(user -> mapearDTO(user))
				.collect(Collectors.toList());
		
	}
	
}
