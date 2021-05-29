package com.br.wendelcabral.startupapi.domain.exception;

public class EntidadeNaoEncontrataExeption extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontrataExeption(String mensagem) {
		super(mensagem);
	}
}
