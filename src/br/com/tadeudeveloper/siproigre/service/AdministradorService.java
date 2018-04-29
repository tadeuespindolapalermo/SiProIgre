package br.com.tadeudeveloper.siproigre.service;

import java.util.List;
import javax.inject.Inject;
import br.com.tadeudeveloper.siproigre.dao.AdministradorDAO;
import br.com.tadeudeveloper.siproigre.model.Administrador;
import br.com.tadeudeveloper.siproigre.model.Log.TipoMensagem;

/**
 * Métodos de negócio relacionados à entidade Administrador
 */
public class AdministradorService extends Service {
	
	private static final long serialVersionUID = -1342052239240631522L;

	@Inject
	private AdministradorDAO administradorDAO;	

	@Inject
	private LogService logService;

	/**
	 * Insere um novo administrador no banco de dados
	 * @param administrador Administrador a ser inserido
	 * @throws ServiceException
	 */
	public void inserir(Administrador administrador) {
		try {
			beginTransaction();
			
			administradorDAO.salvar(administrador);
			logService.log("Administrador inserido: " + administrador, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Altera um administrador cadastrado no banco de dados.
	 * @param administrador
	 * @throws ServiceException
	 */
	public void alterar(Administrador administrador) {
		try {
			beginTransaction();
			
			administradorDAO.alterar(administrador);
			logService.log("Administrador alterado: " + administrador, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Exclui um administrador do banco de dados
	 * @param cpf CPF do administrador a ser excluído
	 * @throws ServiceException
	 */
	public void excluir(String cpf) {
		try {
			beginTransaction();
			
			Administrador administrador = administradorDAO.carregar(cpf, Administrador.class);
			administradorDAO.excluir(administrador);
			logService.log("Usuario excluído: " + cpf, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Lê todos os administradores cadastrados no banco de dados
	 * @return Lista de administradores cadastrados
	 * @throws ServiceException
	 */
	public List<Administrador> listarAdministradores() {
		return administradorDAO.listarAdministradores();
	}		
	
}
