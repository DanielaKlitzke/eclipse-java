package br.com.exception;

import br.com.erros.ESqlErros;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(ESqlErros erro, Class<?> classe, String mensagem) {
		super(erro.getMensagem() + classe.getName() + "&&" + mensagem);
	}

}
