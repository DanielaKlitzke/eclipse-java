package br.com.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.dao.PessoaDAO;
import br.com.erros.ServiceErros;
import br.com.exception.ConexaoException;
import br.com.exception.DAOException;
import br.com.exception.ServiceException;
import br.com.facade.PessoaFacade;
import br.com.model.Pessoa;

@Path("/pessoa")
public class PessoaService {

	private static final String STRING_VAZIA = "";
	private static final String PESSOA_ADICIONADA = "Pessoa adicionada com sucesso!";
	private static final String PESSOA_ALTERADA = "Pessoa alterada com sucesso!";
	private static final String PESSOA_REMOVIDA = "Pessoa removida com sucesso!";
	private static final String CAMPOS_OBRIGATORIOS = "É necessário informar os campos: nome, idade, celular, sexo e idEnderecoPessoa!";
	private static final String ID_ENDERECO_NAO_ENCONTRADO = "Id de endereço não encontrado";
	private static final String PESSOA_NAO_EXISTENTE = "Pessoa não encontrada!";

	private PessoaDAO pessoaDAO;
	private PessoaFacade pessoaFacade;

	@PostConstruct
	private void init() {
		pessoaDAO = new PessoaDAO();
		pessoaFacade = new PessoaFacade();
	}

	@POST
	@Path("/adicionarPessoa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarPessoa(Pessoa pessoa) throws ServiceException, ConexaoException, DAOException {
		String msg = STRING_VAZIA;

		if (pessoaFacade.validarCamposPreenchidosPessoa(pessoa)) {
			if (pessoaFacade.validarEnderecoExistente(pessoa.getIdEnderecoPessoa())) {
				try {
					pessoaDAO.adicionarPessoa(pessoa);
					msg = PESSOA_ADICIONADA;
				} catch (Exception e) {
					throw new ServiceException(ServiceErros.ERRO_ADICIONAR_PESSOAS, this.getClass(), e.getMessage());
				}
			} else {
				msg = ID_ENDERECO_NAO_ENCONTRADO;
			}
		} else {
			msg = CAMPOS_OBRIGATORIOS;
		}

		return msg;
	}

	@GET
	@Path("/listarPessoas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pessoa> listarPessoas() throws ServiceException {
		List<Pessoa> pessoas = null;
		try {
			pessoas = pessoaDAO.listarPessoa();
		} catch (Exception e) {
			throw new ServiceException(ServiceErros.ERRO_LISTAR_PESSOAS, this.getClass(), e.getMessage());
		}
		return pessoas;
	}

	@GET
	@Path("/buscarPessoaPorId/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa buscarPessoasPorId(@PathParam("id") int idPessoa) throws ServiceException {
		Pessoa pessoa = null;
		try {
			pessoa = pessoaDAO.buscarPessoaPorId(idPessoa);
		} catch (Exception e) {
			throw new ServiceException(ServiceErros.ERRO_BUSCAR_PESSOAS, this.getClass(), e.getMessage());
		}
		return pessoa;
	}

	@PUT
	@Path("/alterarPessoa/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String alterarPessoa(Pessoa pessoa, @PathParam("id") int idPessoa)
			throws ServiceException, ConexaoException, DAOException {
		String msg = STRING_VAZIA;

		if (pessoaFacade.validarCamposPreenchidosPessoa(pessoa)) {
			if (pessoaFacade.validarPessoaExistente(idPessoa)) {
				if (pessoaFacade.validarEnderecoExistente(pessoa.getIdEnderecoPessoa())) {
					try {
						pessoaDAO.alterarPessoa(pessoa, idPessoa);
						msg = PESSOA_ALTERADA;
					} catch (Exception e) {
						throw new ServiceException(ServiceErros.ERRO_ALTERAR_PESSOA, this.getClass(), e.getMessage());
					}
				} else {
					msg = ID_ENDERECO_NAO_ENCONTRADO;
				}
			} else {
				msg = PESSOA_NAO_EXISTENTE;
			}
		} else {
			msg = CAMPOS_OBRIGATORIOS;
		}
		return msg;
	}

	@DELETE
	@Path("/removerPessoa/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerPessoa(Pessoa pessoa, @PathParam("id") int idPessoa)
			throws ServiceException, ConexaoException, DAOException {
		String msg = STRING_VAZIA;

		if (pessoaFacade.validarPessoaExistente(idPessoa)) {
			try {
				pessoaDAO.removerPessoa(idPessoa);
				msg = PESSOA_REMOVIDA;
			} catch (Exception e) {
				throw new ServiceException(ServiceErros.ERRO_REMOVER_PESSOA, this.getClass(), e.getMessage());
			}
		} else {
			msg = PESSOA_NAO_EXISTENTE;
		}
		return msg;
	}

}
