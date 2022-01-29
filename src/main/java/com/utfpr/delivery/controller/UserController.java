package com.utfpr.delivery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.delivery.dto.user.UserDTO;
import com.utfpr.delivery.dto.user.UserInputDTO;
import com.utfpr.delivery.entity.User;
import com.utfpr.delivery.mapper.user.UserInputMapper;
import com.utfpr.delivery.mapper.user.UserOutputMapper;
import com.utfpr.delivery.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserOutputMapper userOutputMapper;

	@Autowired
	private UserInputMapper userInputMapper;

	@GetMapping("/{uuid}")
	@ResponseBody
	public UserDTO getUserById(@PathVariable String uuid) {

		User user = userService.getUserByUuid(uuid);

		UserDTO userDTO = userOutputMapper.mapearDTO(user);

		return userDTO;

	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private UserDTO adicionar(@RequestBody @Valid UserInputDTO userInputDTO) {

		User user = userInputMapper.mapearEntity(userInputDTO);

		user = userService.save(user);

		UserDTO userDTO = userOutputMapper.mapearDTO(user);

		return userDTO;

	}

	@PutMapping("/{uuid}")
	@ResponseBody
	private UserDTO alterar(@PathVariable String uuid, @Valid @RequestBody UserInputDTO userInputDTO) {

		User user = userInputMapper.mapearEntity(userInputDTO);

		user = userService.update(uuid, user);

		UserDTO userDTO = userOutputMapper.mapearDTO(user);

		return userDTO;

	}

	@PatchMapping("/{uuid}")
	@ResponseBody
	private User ajustar(@PathVariable String uuid, @RequestBody UserInputDTO userInputDTO) {

		User user = userInputMapper.mapearEntity(userInputDTO);

		return userService.update(uuid, user);

	}

	@DeleteMapping("/{uuid}")
	private ResponseEntity<User> deletar(@PathVariable String uuid) {

		if (userService.delete(uuid)) {

			return ResponseEntity.noContent().build();

		} else {

			return ResponseEntity.badRequest().build();

		}

	}

}
