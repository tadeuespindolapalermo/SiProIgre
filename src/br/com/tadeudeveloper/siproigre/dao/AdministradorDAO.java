package br.com.tadeudeveloper.siproigre.dao;

import java.util.List;
import javax.persistence.Query;
import br.com.tadeudeveloper.siproigre.model.Administrador;

/**
 * Métodos de acesso a dados relacionados à entidade Administrador
 */
public class AdministradorDAO extends DAO {	
	
	private static final long serialVersionUID = -1959666916531247845L;

	/**
	 * Obtém a lista de administradores cadastrados no banco de dados
	 * @return Lista de administradores cadastrados
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<Administrador> listarAdministradores() {
		Query q = criarQuery("SELECT a FROM Administrador a");
		return q.getResultList();
	}	
	
}
