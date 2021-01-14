package br.com.model;

public class Pessoa {

	int idPessoa;
	String nome;
	int idade;
	String celular;
	String sexo;
	int idEnderecoPessoa;

	public Pessoa() {
		super();

	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdEnderecoPessoa() {
		return idEnderecoPessoa;
	}

	public void setIdEnderecoPessoa(int idEnderecoPessoa) {
		this.idEnderecoPessoa = idEnderecoPessoa;
	}

}
