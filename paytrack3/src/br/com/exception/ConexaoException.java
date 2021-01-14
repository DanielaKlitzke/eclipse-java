package br.com.exception;

import br.com.erros.EConexaoErro;

public class ConexaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConexaoException(EConexaoErro erro, String mensagem) {
		super(erro.getMensagem() + "&&" + mensagem);
	}

}
