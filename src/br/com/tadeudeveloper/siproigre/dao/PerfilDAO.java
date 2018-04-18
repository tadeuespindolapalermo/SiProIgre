package br.com.tadeudeveloper.siproigre.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.tadeudeveloper.siproigre.model.Perfil;

/**
 * Métodos de acesso a dados relacionados à entidade Perfil
 */
public class PerfilDAO extends DAO {	
	
	private static final long serialVersionUID = -7318091221171501929L;

	@SuppressWarnings("unchecked")
	public List<Perfil> listarPerfis() {
		Query q = criarQuery("SELECT p FROM Perfil p");
		return q.getResultList();
	}
}
