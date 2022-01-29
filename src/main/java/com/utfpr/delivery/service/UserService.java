package com.utfpr.delivery.service;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.utfpr.delivery.entity.User;
import com.utfpr.delivery.exception.NotFoundException;
import com.utfpr.delivery.repository.UserRepository;
import com.utfpr.delivery.utils.Utils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserByUuid(String uuid) {

		User usuario = userRepository.findByUuid(uuid);

		if (usuario == null) {
			throw new NotFoundException("Usuário não encontrado");
		}

		return usuario;

	}

	public User getUserByEmail(String email) {

		User usuario = userRepository.selectByEmail(email);

		if (usuario == null) {
			throw new NotFoundException("Usuário não encontrado");
		}

		return usuario;

	}

	public User save(User user) {

		return userRepository.save(user);

	}

	public User update(String uuid, User user) {
 
		User userAtual = this.getUserByUuid(uuid);
 
		BeanUtils.copyProperties(user, userAtual, "id", "uuid");

		return userRepository.save(userAtual);

	}

	public User change(String uuid, Map<String, Object> campos) {

		User userAtual = this.getUserByUuid(uuid);

		Utils.merge(userAtual, campos);

		userAtual = this.save(userAtual);

		return userAtual;

	}

	public boolean delete(String uuid) {

		User user = this.getUserByUuid(uuid);

		if (user != null) {

			try {

				userRepository.delete(user);

				return true;

			} catch (EmptyResultDataAccessException ex) {

				System.out.println(ex.getMessage());

			}

		}

		return false;

	}

}
