package br.com.tadeudeveloper.siproigre.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.tadeudeveloper.siproigre.model.Letra;

/**
 * Métodos de acesso a dados relacionados à entidade Letra
 */
public class LetraDAO extends DAO {	
	
	private static final long serialVersionUID = -6568073287317725808L;

	/**
	 * Obtém a lista de letras cadastradas no banco de dados
	 * @return Lista de letras cadastradas
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<Letra> listarLetras() {
		Query q = criarQuery("SELECT l FROM Letra l");
		return q.getResultList();
	}	
	
}
