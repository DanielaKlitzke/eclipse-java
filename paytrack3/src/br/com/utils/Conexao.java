package br.com.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.erros.EConexaoErro;
import br.com.exception.ConexaoException;

public class Conexao {

	private static Connection connection;

	private static String server = "jdbc:mysql://localhost:3306/db_pessoa?useSSL=true&serverTimezone=America/Sao_Paulo";
	private static String user = "daniela";
	private static String password = "123456";

	public static Connection abrirConexao() throws ConexaoException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return connection = DriverManager.getConnection(server, user, password);
		} catch (Exception e) {
			throw new ConexaoException(EConexaoErro.ABRIR_CONEXAO, e.getMessage());
		}
	}

	public static void fecharConexao() throws ConexaoException {
		try {
			if (connection instanceof Connection)
				connection.close();
			connection = null;
		} catch (Exception e) {
			throw new ConexaoException(EConexaoErro.FECHAR_CONEXAO, e.getMessage());
		}
	}

}
