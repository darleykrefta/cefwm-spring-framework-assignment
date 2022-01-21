package com.utfpr.delivery.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.utfpr.delivery.dto.ExceptionDTO;
import com.utfpr.delivery.dto.ExceptionDTO.Validacao;
import com.utfpr.delivery.exception.BadRequestException;
import com.utfpr.delivery.exception.NotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setStatus(status.value());
		exceptionDTO.setMensagem(ex.getMessage());
		
		return handleExceptionInternal(ex, exceptionDTO, null, status, request);
		
	}
	
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setStatus(status.value());
		exceptionDTO.setMensagem(ex.getMessage());
		
		return handleExceptionInternal(ex, exceptionDTO, null, status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Validacao> validacoes = bindingResult.getFieldErrors().stream()
			.map(fieldError -> {
				
				String campo = fieldError.getField();
				
				String erro = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
				
				ExceptionDTO.Validacao validacao = new ExceptionDTO.Validacao();
				validacao.setCampo(campo);
				validacao.setErro(erro);
				
				return validacao;
				
			}).collect(Collectors.toList());
		
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setStatus(status.value());
		exceptionDTO.setMensagem("Alguns campos estão inválidos...");
		exceptionDTO.setValidacoes(validacoes);
		
		return handleExceptionInternal(ex, exceptionDTO, null, status, request);
		
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleUncaughtExceptions(Exception ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setStatus(status.value());
		exceptionDTO.setMensagem("Ocorreu um erro inesperado no programa. Favor consultar o suporte!");
		
		return handleExceptionInternal(ex, exceptionDTO, null, status, request);
		
	}
	
}
