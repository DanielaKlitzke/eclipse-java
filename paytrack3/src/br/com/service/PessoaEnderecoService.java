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

import br.com.dao.EnderecoPessoaDAO;
import br.com.erros.ServiceErros;
import br.com.exception.ConexaoException;
import br.com.exception.DAOException;
import br.com.exception.ServiceException;
import br.com.facade.EnderecoPessoaFacade;
import br.com.model.EnderecoPessoa;

@Path("/pessoaEndereco")
public class PessoaEnderecoService {

	private static final String STRING_VAZIA = "";
	private static final String ENDERECO_ADICIONADO = "Endereço adicionado com sucesso!";
	private static final String ENDERECO_ALTERADO = "Endereço alterado com sucesso!";
	private static final String ENDERECO_REMOVIDO = "Endereço removido com sucesso!";
	private static final String CAMPOS_OBRIGATORIOS = "É necessário informar os campos: cidade, bairro, rua, número e cep!";
	private static final String ERRO_PESSOA_VINCULADA_ENDERECO = "O endereço não pode ser removido porque existem pessoas vincudas a ele!";
	private static final String ENDERECO_NAO_ENCONTRADO = "Endereço não encontrado!";

	private EnderecoPessoaDAO enderecoPessoDAO;
	private EnderecoPessoaFacade enderecoPessoaFacade;

	@PostConstruct
	private void init() {
		enderecoPessoDAO = new EnderecoPessoaDAO();
		enderecoPessoaFacade = new EnderecoPessoaFacade();
	}

	@POST
	@Path("/adicionarEnderecoPessoa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarEnderecoPessoa(EnderecoPessoa enderecoPessoa) throws ServiceException {
		String msg = STRING_VAZIA;

		if (enderecoPessoaFacade.validarCamposPreenchidosEndereco(enderecoPessoa)) {
			try {
				enderecoPessoDAO.adicionarEnderecoPessoa(enderecoPessoa);
				msg = ENDERECO_ADICIONADO;
			} catch (Exception e) {
				throw new ServiceException(ServiceErros.ERRO_ADICIONAR_ENDERECOS, this.getClass(), e.getMessage());
			}
		} else {
			msg = CAMPOS_OBRIGATORIOS;
		}
		return msg;
	}

	@GET
	@Path("/listarEnderecos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EnderecoPessoa> listarEnderecos() throws ServiceException {
		List<EnderecoPessoa> enderecoPessoas = null;
		try {
			enderecoPessoas = enderecoPessoDAO.listarEnderecoPessoa();
		} catch (Exception e) {
			throw new ServiceException(ServiceErros.ERRO_LISTAR_ENDERECO, this.getClass(), e.getMessage());
		}
		return enderecoPessoas;
	}

	@GET
	@Path("/buscarEnderecoPorId/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public EnderecoPessoa buscarEnderecoPorId(@PathParam("id") int idEndereco) throws ServiceException {
		EnderecoPessoa enderecoPessoa = null;
		try {
			enderecoPessoa = enderecoPessoDAO.buscarEnderecoPessoaPorId(idEndereco);
		} catch (Exception e) {
			throw new ServiceException(ServiceErros.ERRO_BUSCAR_ENDERECO, this.getClass(), e.getMessage());
		}
		return enderecoPessoa;
	}

	@PUT
	@Path("/alterarEndereco/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String alterarEndereco(EnderecoPessoa enderecoPessoa, @PathParam("id") int idEndereco)
			throws ServiceException, ConexaoException, DAOException {
		String msg = STRING_VAZIA;

		if (enderecoPessoaFacade.validarCamposPreenchidosEndereco(enderecoPessoa)) {
			if (enderecoPessoaFacade.validarEnderecoExistente(idEndereco)) {
				try {
					enderecoPessoDAO.alterarEnderecoPessoa(enderecoPessoa, idEndereco);
					msg = ENDERECO_ALTERADO;
				} catch (Exception e) {
					throw new ServiceException(ServiceErros.ERRO_ALTERAR_ENDERECO, this.getClass(), e.getMessage());
				}

			} else {
				msg = ENDERECO_NAO_ENCONTRADO;
			}
		} else {
			msg = CAMPOS_OBRIGATORIOS;
		}
		return msg;
	}

	@DELETE
	@Path("/removerEndereco/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerEndereco(EnderecoPessoa enderecoPessoa, @PathParam("id") int idEndereco)
			throws ServiceException, ConexaoException, DAOException {
		String msg = STRING_VAZIA;

		if (enderecoPessoaFacade.validarPessoasVinculadasAoEndereco(idEndereco)) {
			if (enderecoPessoaFacade.validarEnderecoExistente(idEndereco)) {
				try {
					enderecoPessoDAO.removerEnderecoPessoa(idEndereco);
					msg = ENDERECO_REMOVIDO;
				} catch (Exception e) {
					throw new ServiceException(ServiceErros.ERRO_REMOVER_ENDERECO, this.getClass(), e.getMessage());
				}
			} else {
				msg = ENDERECO_NAO_ENCONTRADO;
			}
		} else {
			msg = ERRO_PESSOA_VINCULADA_ENDERECO;
		}

		return msg;
	}

}
