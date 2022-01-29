package com.utfpr.delivery.mapper.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utfpr.delivery.dto.user.UserInputDTO;
import com.utfpr.delivery.entity.User;

@Component
public class UserInputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public User mapearEntity(UserInputDTO usereInputDTO) {
		
		User usere = modelMapper.map(usereInputDTO, User.class);
		
		return usere;
		
	}
	
	public List<User> mapearLista(List<UserInputDTO> usereInputDTOs) {
		
		return usereInputDTOs.stream()
				.map(usereInputDTO -> mapearEntity(usereInputDTO))
				.collect(Collectors.toList());
		
	}
	
}
