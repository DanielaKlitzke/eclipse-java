package br.com.exception;

import br.com.erros.ServiceErros;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException(ServiceErros erro, Class<?> classe, String mensagem) {
		super(erro.getMensagem() + classe.getName() + "&&" + mensagem);
	}
	
}
