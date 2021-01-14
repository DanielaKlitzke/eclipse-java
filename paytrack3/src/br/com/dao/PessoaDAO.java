package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.erros.ESqlErros;
import br.com.exception.ConexaoException;
import br.com.exception.DAOException;
import br.com.model.Pessoa;
import br.com.utils.Conexao;

public class PessoaDAO {

	public List<Pessoa> listarPessoa() throws ConexaoException, DAOException {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Connection conexao = Conexao.abrirConexao();

		String sql = "SELECT * FROM TB_PESSOA";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setCelular(rs.getString("celular"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setIdEnderecoPessoa(rs.getInt("id_endereco_pessoa"));

				pessoas.add(pessoa);

			}
			return pessoas;
		} catch (Exception e) {
			throw new DAOException(ESqlErros.CONSULTAR_TABELA, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public Pessoa buscarPessoaPorId(int idPessoa) throws ConexaoException, DAOException {
		Pessoa pessoa = null;

		Connection conexao = Conexao.abrirConexao();

		String sql = "SELECT * FROM TB_PESSOA WHERE ID_PESSOA = ?";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, idPessoa);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setIdPessoa(rs.getInt("id_pessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setCelular(rs.getString("celular"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setIdEnderecoPessoa(rs.getInt("id_endereco_pessoa"));

			}
			return pessoa;

		} catch (Exception e) {
			throw new DAOException(ESqlErros.CONSULTAR_TABELA, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public void adicionarPessoa(Pessoa pessoa) throws ConexaoException, DAOException {

		Connection conexao = Conexao.abrirConexao();

		String sql = "INSERT INTO TB_PESSOA(NOME, IDADE, CELULAR, SEXO, ID_ENDERECO_PESSOA) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, pessoa.getNome());
			st.setInt(2, pessoa.getIdade());
			st.setString(3, pessoa.getCelular());
			st.setString(4, pessoa.getSexo());
			st.setInt(5, pessoa.getIdEnderecoPessoa());
			st.execute();

		} catch (Exception e) {
			throw new DAOException(ESqlErros.ERRO_AO_INSERIR_DADOS, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public void alterarPessoa(Pessoa pessoa, int id) throws ConexaoException, DAOException {

		Connection conexao = Conexao.abrirConexao();

		String sql = "UPDATE TB_PESSOA SET NOME = ?, IDADE = ?, CELULAR = ?, SEXO = ?, ID_ENDERECO_PESSOA = ? WHERE ID_PESSOA = ?";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, pessoa.getNome());
			st.setInt(2, pessoa.getIdade());
			st.setString(3, pessoa.getCelular());
			st.setString(4, pessoa.getSexo());
			st.setInt(5, pessoa.getIdEnderecoPessoa());
			st.setInt(6, id);
			st.execute();

		} catch (Exception e) {
			throw new DAOException(ESqlErros.ALTERAR_DADOS, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public void removerPessoa(int idPessoa) throws ConexaoException, DAOException {

		Connection conexao = Conexao.abrirConexao();

		String sql = "DELETE FROM TB_PESSOA WHERE ID_PESSOA = ?";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, idPessoa);
			st.execute();

		} catch (Exception e) {
			throw new DAOException(ESqlErros.EXCLUIR_DADOS, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public boolean verificarPessoaVinculadaEndereco(int idEnderecoPessoa) throws ConexaoException, DAOException {
		boolean existePessoa = false;

		Connection conexao = Conexao.abrirConexao();

		String sql = "SELECT * FROM TB_PESSOA WHERE ID_ENDERECO_PESSOA = ?";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, idEnderecoPessoa);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				existePessoa = true;
			}
			return existePessoa;

		} catch (Exception e) {
			throw new DAOException(ESqlErros.CONSULTAR_TABELA, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

}
