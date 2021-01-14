package br.com.facade;

import br.com.dao.EnderecoPessoaDAO;
import br.com.dao.PessoaDAO;
import br.com.exception.ConexaoException;
import br.com.exception.DAOException;
import br.com.model.EnderecoPessoa;
import br.com.model.Pessoa;

public class PessoaFacade {

	public boolean validarCamposPreenchidosPessoa(Pessoa pessoa) {
		boolean camposValidos = true;
		if (pessoa.getNome() == null || pessoa.getIdade() == 0 || pessoa.getCelular() == null
				|| pessoa.getSexo() == null || pessoa.getIdEnderecoPessoa() == 0) {
			camposValidos = false;
		}
		return camposValidos;
	}

	public boolean validarEnderecoExistente(int idEndereco) throws ConexaoException, DAOException {
		boolean enderecoExistente = false;
		EnderecoPessoa enderecoPessoa = new EnderecoPessoa();
		EnderecoPessoaDAO dao = new EnderecoPessoaDAO();

		enderecoPessoa = dao.buscarEnderecoPessoaPorId(idEndereco);
		if (enderecoPessoa != null) {
			enderecoExistente = true;
		}

		return enderecoExistente;
	}

	public boolean validarPessoaExistente(int idPessoa) throws ConexaoException, DAOException {
		boolean pessoaExistente = false;
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoa = new Pessoa();
		pessoa = dao.buscarPessoaPorId(idPessoa);

		if (pessoa != null) {
			pessoaExistente = true;
		}

		return pessoaExistente;
	}

}
