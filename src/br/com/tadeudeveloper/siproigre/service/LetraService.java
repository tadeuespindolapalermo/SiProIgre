package br.com.tadeudeveloper.siproigre.service;

import java.util.List;

import javax.inject.Inject;

import br.com.tadeudeveloper.siproigre.dao.LetraDAO;
import br.com.tadeudeveloper.siproigre.model.Letra;
//import br.com.tadeudeveloper.siproigre.model.Log.TipoMensagem;

/**
 * Métodos de negócio relacionados à entidade Letra
 */
public class LetraService extends Service {
	
	private static final long serialVersionUID = -3339558127567452158L;

	@Inject
	private LetraDAO letraDAO;	

	/*@Inject
	private LogService logService;*/

	/**
	 * Insere uma nova letra no banco de dados	
	 */
	public void inserir(Letra letra) {
		try {
			beginTransaction();		
			
			letraDAO.salvar(letra);
			//logService.log("Letra inserida: " + letra, TipoMensagem.INFO);		
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();		
			throw e;
		}
		
	}
	
	/**
	 * Altera uma letra cadastrada no banco de dados.	 
	 */
	public void alterar(Letra letra) {
		try {
			beginTransaction();		
			
			letraDAO.alterar(letra);
			//logService.log("Letra alterada: " + letra, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Exclui uma letra do banco de dados	 
	 */
	public void excluir(Integer id) {
		try {
			beginTransaction();
			
			Letra letra = letraDAO.carregar(id, Letra.class);
			letraDAO.excluir(letra);
			//logService.log("Letra excluída: " + id, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Lista todas as letras cadastradas no banco de dados	
	 */
	public List<Letra> listarLetras() {
		return letraDAO.listarLetras();
	}		
	
}
