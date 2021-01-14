package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.erros.ESqlErros;
import br.com.exception.ConexaoException;
import br.com.exception.DAOException;
import br.com.model.EnderecoPessoa;
import br.com.utils.Conexao;

public class EnderecoPessoaDAO {

	public List<EnderecoPessoa> listarEnderecoPessoa() throws ConexaoException, DAOException {
		List<EnderecoPessoa> enderecoPessoas = new ArrayList<EnderecoPessoa>();
		Connection conexao = Conexao.abrirConexao();

		String sql = "SELECT * FROM TB_ENDERECO_PESSOA";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				EnderecoPessoa enderecoPessoa = new EnderecoPessoa();
				enderecoPessoa.setIdEnderecoPessoa(rs.getInt("id_endereco_pessoa"));
				enderecoPessoa.setCidade(rs.getString("cidade"));
				enderecoPessoa.setBairro(rs.getString("bairro"));
				enderecoPessoa.setRua(rs.getString("rua"));
				enderecoPessoa.setNumero(rs.getString("numero"));
				enderecoPessoa.setCep(rs.getString("cep"));

				enderecoPessoas.add(enderecoPessoa);

			}
			return enderecoPessoas;
		} catch (Exception e) {
			throw new DAOException(ESqlErros.CONSULTAR_TABELA, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public EnderecoPessoa buscarEnderecoPessoaPorId(int idEnderecoPessoa) throws ConexaoException, DAOException {
		EnderecoPessoa enderecoPessoa = null;

		Connection conexao = Conexao.abrirConexao();

		String sql = "SELECT * FROM TB_ENDERECO_PESSOA WHERE ID_ENDERECO_PESSOA = ?";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, idEnderecoPessoa);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				enderecoPessoa = new EnderecoPessoa();
				enderecoPessoa.setIdEnderecoPessoa(rs.getInt("id_endereco_pessoa"));
				enderecoPessoa.setCidade(rs.getString("cidade"));
				enderecoPessoa.setBairro(rs.getString("bairro"));
				enderecoPessoa.setRua(rs.getString("rua"));
				enderecoPessoa.setNumero(rs.getString("numero"));
				enderecoPessoa.setCep(rs.getString("cep"));

			}
			return enderecoPessoa;

		} catch (Exception e) {
			throw new DAOException(ESqlErros.CONSULTAR_TABELA, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public void adicionarEnderecoPessoa(EnderecoPessoa enderecoPessoa) throws ConexaoException, DAOException {

		Connection conexao = Conexao.abrirConexao();

		String sql = "INSERT INTO TB_ENDERECO_PESSOA(CIDADE, BAIRRO, RUA, NUMERO, CEP) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, enderecoPessoa.getCidade());
			st.setString(2, enderecoPessoa.getBairro());
			st.setString(3, enderecoPessoa.getRua());
			st.setString(4, enderecoPessoa.getNumero());
			st.setString(5, enderecoPessoa.getCep());
			st.execute();

		} catch (Exception e) {
			throw new DAOException(ESqlErros.ERRO_AO_INSERIR_DADOS, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public void alterarEnderecoPessoa(EnderecoPessoa enderecoPessoa, int id) throws ConexaoException, DAOException {

		Connection conexao = Conexao.abrirConexao();

		String sql = "UPDATE TB_ENDERECO_PESSOA SET CIDADE = ?, BAIRRO = ?, RUA = ?, NUMERO = ?, CEP = ? WHERE ID_ENDERECO_PESSOA = ?";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, enderecoPessoa.getCidade());
			st.setString(2, enderecoPessoa.getBairro());
			st.setString(3, enderecoPessoa.getRua());
			st.setString(4, enderecoPessoa.getNumero());
			st.setString(5, enderecoPessoa.getCep());
			st.setInt(6, id);
			st.execute();

		} catch (Exception e) {
			throw new DAOException(ESqlErros.ALTERAR_DADOS, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

	public void removerEnderecoPessoa(int idEnderecoPessoa) throws ConexaoException, DAOException {

		Connection conexao = Conexao.abrirConexao();

		String sql = "DELETE FROM TB_ENDERECO_PESSOA WHERE ID_ENDERECO_PESSOA = ?";

		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, idEnderecoPessoa);
			st.execute();

		} catch (Exception e) {
			throw new DAOException(ESqlErros.EXCLUIR_DADOS, this.getClass(), e.getMessage());
		} finally {
			Conexao.fecharConexao();
		}
	}

}
