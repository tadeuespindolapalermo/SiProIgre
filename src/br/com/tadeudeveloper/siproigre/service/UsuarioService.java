package br.com.tadeudeveloper.siproigre.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.com.tadeudeveloper.siproigre.dao.UsuarioDAO;
import br.com.tadeudeveloper.siproigre.dao.PerfilDAO;
import br.com.tadeudeveloper.siproigre.model.Usuario;
import br.com.tadeudeveloper.siproigre.model.Perfil;
import br.com.tadeudeveloper.siproigre.model.Log.TipoMensagem;

/**
 * Métodos de negócio relacionados à entidade Usuario
 */
public class UsuarioService extends Service {
		
	private static final long serialVersionUID = 7921064216870234605L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject	
	private PerfilDAO perfilDAO;

	@Inject
	private LogService logService;

	/**
	 * Insere um novo usuário no banco de dados	
	 */
	public void inserir(Usuario usuario) {
		try {
			beginTransaction();
			
			usuarioDAO.salvar(usuario);
			logService.log("Usuario inserido: " + usuario, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Alter um usuário cadastrado no banco de dados.	
	 */
	public void alterar(Usuario usuario) {
		try {
			beginTransaction();
			
			usuarioDAO.alterar(usuario);
			logService.log("Usuario alterado: " + usuario, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Exclui um usuário do banco de dados	
	 */
	public void excluir(String cpf) {
		try {
			beginTransaction();
			
			Usuario usuario = usuarioDAO.carregar(cpf, Usuario.class);
			usuarioDAO.excluir(usuario);
			logService.log("Usuario excluído: " + cpf, TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
	/**
	 * Lista todos os usuários cadastrados no banco de dados	
	 */
	public List<Usuario> listarUsuarios() {
		return usuarioDAO.listarUsuarios();
	}
	
	/**
	 * Obtém todos os usuários associados a um perfil específico	
	 */
	public List<Usuario> obterUsuariosPerfil(Integer perfilId) {
		return usuarioDAO.obterUsuariosPerfil(perfilId);
	}
	
	/**
	 * Associa determinado usuário à um perfil	
	 */
	public void associarUsuariosPerfil(String[] cpfs, Integer perfilId) {
		try {
			beginTransaction();

			// Remove todos os usuários do perfil, para manter a consistência			
			List<Usuario> usuarios = usuarioDAO.obterUsuariosPerfil(perfilId);
			for (Usuario usuario : usuarios) {
				usuario.setPerfil(null);
			}
			
			// Carrega o perfil
			Perfil perfil = perfilDAO.carregar(perfilId, Perfil.class);
						
			for (String cpf : cpfs) {
				// Atualiza o perfil de cada usuário do array
				Usuario usuario = usuarioDAO.carregar(cpf, Usuario.class);
				usuario.setPerfil(perfil);
			}
			
			logService.log("Usuários associados à perfil " + perfilId + ":" + Arrays.toString(cpfs), TipoMensagem.INFO);
			
			commitTransaction();
		
		} catch (RuntimeException e) {
			rollbackTransaction();
			throw e;
		}
	}
	
}
