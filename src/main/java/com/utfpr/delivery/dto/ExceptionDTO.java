package com.utfpr.delivery.dto;

import java.util.List;

import lombok.Data;

@Data
public class ExceptionDTO {

	private Integer status;
	
	private String mensagem;
	
	private List<Validacao> validacoes;
	
	@Data
	public static class Validacao {
		
		private String campo;
		
		private String erro;
		
	}
	
}
