package br.com.erros;

public enum ServiceErros {

	ERRO_LISTAR_PESSOAS("Erro ao listar pessoas"), //
	ERRO_ADICIONAR_PESSOAS("Erro ao adicionar a pessoa"), //
	ERRO_BUSCAR_PESSOAS("Erro ao buscar pela pessoa"), //
	ERRO_ALTERAR_PESSOA("Erro ao alterar a pessoa"), //
	ERRO_REMOVER_PESSOA("Erro ao remover a pessoa"), //
	ERRO_LISTAR_ENDERECO("Erro ao listar pessoas: "), //
	ERRO_ADICIONAR_ENDERECOS("Erro ao adicionar a pessoa"), //
	ERRO_BUSCAR_ENDERECO("Erro ao buscar pela pessoa"), //
	ERRO_ALTERAR_ENDERECO("Erro ao alterar a pessoa"), //
	ERRO_REMOVER_ENDERECO("Erro ao remover a pessoa");

	private String mensagem = "";

	private ServiceErros(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	@Override
	public String toString() {
		return getMensagem();
	}

}
