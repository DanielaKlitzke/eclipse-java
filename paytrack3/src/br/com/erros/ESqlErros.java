package br.com.erros;

public enum ESqlErros {

	CONSULTAR_TABELA("Erro ao consultar dados: "), //
	ALTERAR_DADOS("Erro ao alterar dados: "), //
	EXCLUIR_DADOS("Erro ao excluir dados: "), //
	ERRO_AO_INSERIR_DADOS("Erro ao inserir dados: ");

	private String mensagem = "";

	private ESqlErros(String mensagem) {
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
