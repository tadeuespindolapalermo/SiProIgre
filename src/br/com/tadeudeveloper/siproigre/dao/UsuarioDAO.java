package br.com.tadeudeveloper.siproigre.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.tadeudeveloper.siproigre.model.Usuario;
/**
 * Métodos de acesso a dados relacionados à entidade Usuario
 */
public class UsuarioDAO extends DAO {	
	
	private static final long serialVersionUID = 9092740838760722204L;

	/**
	 * Obtém a lista de usuários cadastrados no banco de dados
	 * @return Lista de usuários cadastrados
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() {
		Query q = criarQuery("SELECT u FROM Usuario u");
		return q.getResultList();
	}
	
	/**
	 * Obtém a lista de usuários de um perfil
	 * @param perfilId Perfil onde os usuários estão
	 * @return Lista de usuários
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> obterUsuariosPerfil(Integer perfilId) {
		Query q = criarQuery("SELECT u FROM Usuario u WHERE u.perfil.id = " + perfilId);
		return q.getResultList();
	}	
	
}
