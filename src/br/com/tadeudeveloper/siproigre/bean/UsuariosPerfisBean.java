package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.tadeudeveloper.siproigre.model.Usuario;
import br.com.tadeudeveloper.siproigre.model.Perfil;
import br.com.tadeudeveloper.siproigre.service.UsuarioService;
import br.com.tadeudeveloper.siproigre.service.PerfilService;

/**
 * Bean da tela de associação entre usuarios e perfils
 */
@Named("usuariosPerfis")
@SessionScoped
public class UsuariosPerfisBean extends AbstractBean {

	private static final long serialVersionUID = 1873933238163347234L;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private PerfilService perfilService;

	private List<Perfil> perfis;

	private List<Usuario> usuarios;

	private Integer perfilId;

	private String[] usuariosSelecionados;

	/**
	 * Evento disparado quando a caixa de seleção de perfil tem seu valor alterado
	 * @param event
	 */
	public void mudouPerfil(ValueChangeEvent event) {
		try {
			Integer perfilId = (Integer) event.getNewValue();

			// Obtém a lista de usuários cadastrados para o perfil selecionado
			List<Usuario> usuariosPerfil = usuarioService.obterUsuariosPerfil(perfilId);

			// Popula o array 'usuariosSelecionados' com os CPFs, para que os checkboxes
			// apareçam marcados na tela
			usuariosSelecionados = new String[usuariosPerfil.size()];
			int i = 0;
			for (Usuario usuario : usuariosPerfil) {
				usuariosSelecionados[i++] = usuario.getCpf().toString();
			}

			this.perfilId = perfilId;

			// O código abaixo forma a recriação da view, fazendo com o JSF navegue para a tela representada
			// pelo outcome 'usuarios_perfis'. Sem isto, os dados na tela não serão atualizados
			FacesContext context = FacesContext.getCurrentInstance();
			NavigationHandler navHandler = context.getApplication().getNavigationHandler();
			navHandler.handleNavigation(context, null, "usuarios_perfis");
		} catch (Exception e) {
			handleException(e);
		}
	}

	/**
	 * Associa usuarios a um perfil
	 * @return
	 */
	public String associarUsuarios() {
		try {
			usuarioService.associarUsuariosPerfil(usuariosSelecionados, perfilId);
			perfis = null;
			usuarios = null;
			perfilId = null;
			usuariosSelecionados = null;
			return "index";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Obtém todas as perfis cadastradas no banco de dados
	 * @return Lista de perfis
	 */
	public List<Perfil> getPerfis() {
		try {
			if (perfis == null) {
				perfis = perfilService.listarPerfis();
			}
			return perfis;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Obtém todos os usuarios cadastrados no banco de dados
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

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Integer perfilId) {
		this.perfilId = perfilId;
	}

	public String[] getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(String[] usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}
}
