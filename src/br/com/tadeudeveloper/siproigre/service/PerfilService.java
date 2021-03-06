package br.com.tadeudeveloper.siproigre.service;

import java.util.List;

import javax.inject.Inject;

import br.com.tadeudeveloper.siproigre.dao.PerfilDAO;
import br.com.tadeudeveloper.siproigre.model.Perfil;
import br.com.tadeudeveloper.siproigre.model.Log.TipoMensagem;

/**
 * Métodos de negócio relacionados à entidade Perfil
 */
public class PerfilService extends Service {
	
	private static final long serialVersionUID = -670689968792982919L;

	@Inject
	private PerfilDAO perfilDAO;

	@Inject
	private LogService logService;

	/**
	 * Insere um novo perfil no banco de dados	 
	 */
	public void inserir(Perfil perfil) {
		try {
			beginTransaction();
			
			perfilDAO.salvar(perfil);
			logService.log("Perfil inserido: " + perfil, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Altera um perfil cadastrado no banco de dados	
	 */
	public void alterar(Perfil perfil) {
		try {
			beginTransaction();
			
			perfilDAO.alterar(perfil);
			logService.log("Perfil alterado: " + perfil, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Exclui um perfil cadastrado no banco de dados	
	 */
	public void excluir(Integer id) {
		try {
			beginTransaction();
			
			Perfil perfil = perfilDAO.carregar(id, Perfil.class);
			perfilDAO.excluir(perfil);
			logService.log("Perfil excluído: " + id, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Lista todos os perfis cadastrados no banco de dados	 
	 */
	public List<Perfil> listarPerfis() {
		return perfilDAO.listarPerfis();
	}
	
}
