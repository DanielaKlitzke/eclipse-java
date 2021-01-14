package br.com.model;

public class EnderecoPessoa {

	int idEnderecoPessoa;
	String cidade;
	String bairro;
	String rua;
	String numero;
	String cep;

	public EnderecoPessoa() {
		super();

	}

	public int getIdEnderecoPessoa() {
		return idEnderecoPessoa;
	}

	public void setIdEnderecoPessoa(int idEnderecoPessoa) {
		this.idEnderecoPessoa = idEnderecoPessoa;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
