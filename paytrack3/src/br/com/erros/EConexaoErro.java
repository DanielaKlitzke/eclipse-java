package br.com.erros;

public enum EConexaoErro {

	ABRIR_CONEXAO("Erro de Conexão: Não foi possível estabeler conexão com a base de dados"),
	FECHAR_CONEXAO("Erro de Conexão: Não foi possível encerrar conexão com a base de dados");

	private final String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	private EConexaoErro(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return getMensagem();
	}

}
