package br.com.tadeudeveloper.siproigre.bean;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.tadeudeveloper.siproigre.model.Log.TipoMensagem;
import br.com.tadeudeveloper.siproigre.service.LogService;

/**
 * Superclasse de todos os CDI beans da aplicação
 */
public abstract class AbstractBean implements Serializable {	
	
	private static final long serialVersionUID = -7142665781475580005L;
	
	@Inject
	private LogService logService;
	
	/**
	 * Faz o log de erro da exceção no banco de dados e imprime a stack trace no console.
	 * @param exception Exceção ocorrida
	 */
	protected void handleException(Exception exception) {
		try {
			exception.printStackTrace();
			logService.log(exception.toString(), TipoMensagem.ERRO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
