package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.tadeudeveloper.siproigre.model.Usuario;
import br.com.tadeudeveloper.siproigre.service.UsuarioService;

/**
 * Bean da tela de cadastro de usuarios
 */
@Named("usuarios")
@SessionScoped
public class UsuariosBean extends AbstractBean {
	
	private static final long serialVersionUID = 2192678360365896839L;
	
	@Inject
	private UsuarioService usuarioService;

	private List<Usuario> usuarios;

	private Usuario usuario;

	private boolean alterar;

	/**
	 * Obtém a lista de usuarios
	 * @return Lista de usuarios
	 */
	public List<Usuario> getUsuarios() {
		try {
			if (usuarios == null) {
				usuarios = usuarioService.listarUsuarios();
			}
			return usuarios;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Abre a tela de edição de usuario
	 * @param usuario Usuario a editar
	 * @return
	 */
	public String alterar(Usuario usuario) {
		this.usuario = usuario;
		this.alterar = true;
		return "editar_usuario";
	}

	/**
	 * Abre a tela de cadastro de usuario
	 * @param usuario
	 * @return
	 */
	public String novoUsuario() {
		usuario = new Usuario();
		alterar = false;
		return "editar_usuario";
	}	
	
	/**
	 * Exclui um usuario
	 * @param usuario Usuario para excluir
	 * @return
	 */
	public String excluir(Usuario usuario) {
		try {
			usuarioService.excluir(usuario.getCpf());
			usuarios = null;
			
			// Após a exclusão, faz um redirect
			return "listar_usuarios?faces-redirect=true";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Cadastra ou atualiza um usuario (depende do estado da flag 'alterar')
	 * @return
	 */
	public String salvar() {
		
		System.out.println(usuario.getDataNascimento());
		try {
			if (alterar) {
				usuarioService.alterar(usuario);
			} else {
				usuarioService.inserir(usuario);
			}
			usuario = null;
			usuarios = null;
			alterar = false;
			return "listar_usuarios?faces-redirect=true";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

}
