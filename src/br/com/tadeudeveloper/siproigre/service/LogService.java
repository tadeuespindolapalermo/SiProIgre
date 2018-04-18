package br.com.tadeudeveloper.siproigre.service;

import java.util.Date;

import javax.inject.Inject;

import br.com.tadeudeveloper.siproigre.dao.LogDAO;
import br.com.tadeudeveloper.siproigre.model.Log;
import br.com.tadeudeveloper.siproigre.model.Log.TipoMensagem;

/**
 * Métodos de negécio relacionados ao logging
 */
public class LogService extends Service {	
	
	private static final long serialVersionUID = 1719358505693741651L;
	
	@Inject	
	private LogDAO logDAO;

	/**
	 * Insere uma nova mensagem de log no banco de dados
	 * @param mensagem Mensagem de log
	 * @param tipo Tipo da mensagem
	 * @throws ServiceException
	 */
	public void log(String mensagem, TipoMensagem tipo) {
		try {
			beginTransaction();
			
			Log log = new Log();
			log.setData(new Date());
			log.setTipo(tipo);
			log.setMensagem(mensagem);
			logDAO.salvar(log);
			
			commitTransaction();

		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
}
