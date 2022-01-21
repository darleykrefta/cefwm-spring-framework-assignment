package com.utfpr.delivery.controller;

import java.util.List;

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

import com.utfpr.delivery.dto.RestauranteDTO;
import com.utfpr.delivery.dto.RestauranteInputDTO;
import com.utfpr.delivery.dto.RestauranteResumoDTO;
import com.utfpr.delivery.entity.Restaurante;
import com.utfpr.delivery.mapper.RestauranteInputMapper;
import com.utfpr.delivery.mapper.RestauranteOutputMapper;
import com.utfpr.delivery.mapper.RestauranteResumoOutputMapper;
import com.utfpr.delivery.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteResumoOutputMapper restauranteResumoOutputMapper;
	
	@Autowired
	private RestauranteOutputMapper restauranteOutputMapper;
	
	@Autowired
	private RestauranteInputMapper restauranteInputMapper;
	
	@GetMapping
	@ResponseBody
	public List<RestauranteResumoDTO> listarTodosOsRestaurantes() {
		
		List<Restaurante> restaurantes = restauranteService.listarTodosOsRestaurantes();
		
		List<RestauranteResumoDTO> restauranteResumoDTOs = restauranteResumoOutputMapper.mapearLista(restaurantes);
		
		return restauranteResumoDTOs;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseBody
	public RestauranteDTO getRestauranteById(@PathVariable String uuid) {
		
		Restaurante restaurante = restauranteService.getRestauranteByUuid(uuid);
		
		RestauranteDTO restauranteDTO = restauranteOutputMapper.mapearDTO(restaurante);
		
		return restauranteDTO;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
		
		Restaurante restaurante = restauranteInputMapper.mapearEntity(restauranteInputDTO);
		
		restaurante = restauranteService.salvar(restaurante);
		
		RestauranteDTO restauranteDTO = restauranteOutputMapper.mapearDTO(restaurante);
		
		return restauranteDTO;
		
	}
	
	@PutMapping("/{uuid}")
	@ResponseBody
	private RestauranteDTO alterar(@PathVariable String uuid, @Valid @RequestBody RestauranteInputDTO restauranteInputDTO) {
		
		Restaurante restaurante = restauranteInputMapper.mapearEntity(restauranteInputDTO);
		
		restaurante = restauranteService.alterar(uuid, restaurante);
		
		RestauranteDTO restauranteDTO = restauranteOutputMapper.mapearDTO(restaurante);
		
		return restauranteDTO;
		
	}
	
	@PatchMapping("/{uuid}")
	@ResponseBody
	private Restaurante ajustar(@PathVariable String uuid, @RequestBody RestauranteInputDTO restauranteInputDTO) {
		
		Restaurante restaurante = restauranteInputMapper.mapearEntity(restauranteInputDTO);
		
		return restauranteService.alterar(uuid, restaurante);
		
	}
	
	@DeleteMapping("/{uuid}")
	private ResponseEntity<Restaurante> deletar(@PathVariable String uuid) {
		
		if (restauranteService.excluir(uuid)) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}

}
