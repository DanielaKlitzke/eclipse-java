package br.com.facade;

import br.com.dao.EnderecoPessoaDAO;
import br.com.dao.PessoaDAO;
import br.com.exception.ConexaoException;
import br.com.exception.DAOException;
import br.com.model.EnderecoPessoa;

public class EnderecoPessoaFacade {

	public boolean validarCamposPreenchidosEndereco(EnderecoPessoa enderecoPessoa) {
		boolean camposValidos = true;
		if (enderecoPessoa.getCidade() == null || enderecoPessoa.getBairro() == null || enderecoPessoa.getRua() == null
				|| enderecoPessoa.getNumero() == null || enderecoPessoa.getCep() == null) {
			camposValidos = false;
		}
		return camposValidos;
	}

	public boolean validarPessoasVinculadasAoEndereco(int idEndereco) throws ConexaoException, DAOException {
		boolean podeRemover = true;
		PessoaDAO dao = new PessoaDAO();

		if (dao.verificarPessoaVinculadaEndereco(idEndereco)) {
			podeRemover = false;
		}
		return podeRemover;

	}

	public boolean validarEnderecoExistente(int idEndereco) throws ConexaoException, DAOException {
		boolean enderecoExistente = false;
		EnderecoPessoaDAO dao = new EnderecoPessoaDAO();
		EnderecoPessoa enderecoPessoa = new EnderecoPessoa();
		enderecoPessoa = dao.buscarEnderecoPessoaPorId(idEndereco);

		if (enderecoPessoa != null) {
			enderecoExistente = true;
		}
		return enderecoExistente;
	}
}
