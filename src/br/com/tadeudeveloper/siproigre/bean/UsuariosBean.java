package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.tadeudeveloper.siproigre.model.Usuario;
import br.com.tadeudeveloper.siproigre.service.UsuarioService;

/**
 * Bean do CRUD de Usuários
 */
@Named("usuarios")
@SessionScoped
public class UsuariosBean extends AbstractBean {	
	
	private static final long serialVersionUID = -7480895540388668981L;

	@Inject
	private UsuarioService usuarioService;	

	private List<Usuario> usuarios;

	private Usuario usuario;

	private boolean alterar = false;	

	/**
	 * Inicia a view de Usuários listando todos os usuários na tabela
	 */	
	@PostConstruct
	public void listar() {
		try {			
			usuarios = usuarioService.listarUsuarios();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários!");
			erro.printStackTrace();
		}
	}
	
	/**
	 * Prepara o dialog de cadastro de usuários para a inserção de um novo usuário
	 */	
	public void novo() {
		try {
			alterar = false;			
			usuario = new Usuario();		
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar criar um novo usuário!");
			erro.printStackTrace();
		}
	}	
	
	/**
	 * Prepara o dialog de cadastro de usuários para a atualização de um usuário selecionado
	 */	
	public void editar(ActionEvent evento){
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");			
			alterar = true;			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o usuário!");
			erro.printStackTrace();
		}	
	}	
	
	/**
	 * Exclui um usuário selecionado	 
	 */	
	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			usuarioService.excluir(usuario.getCpf());			

			usuarios = usuarioService.listarUsuarios();

			Messages.addGlobalInfo("Usuário removido com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o usuário!");
			erro.printStackTrace();
		}
	}	

	/**
	 * Insere um NOVO USUÁRIO se o atributo alterar estiver setado com valor false
	 * Altera um USUÁRIO EXISTENTE se o atributo alterar estiver setado com valor true
	 */	
	public void salvar() {
		try {			
			if (!alterar) {
				usuarioService.inserir(usuario);			
				
				usuario = new Usuario();			

				usuarios = usuarioService.listarUsuarios();

				Messages.addGlobalInfo("Usuário inserido com sucesso!");
			} else {
				usuarioService.alterar(usuario);
				usuarios = usuarioService.listarUsuarios();
				Messages.addGlobalInfo("Usuário alterado com sucesso!");
			}			
		} catch (RuntimeException erro) {
			usuarios = usuarioService.listarUsuarios();
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o usuário!");
			erro.printStackTrace();
		}
	}	
	
	// Getters e Setters
	public Usuario getUsuario() {
		// Lógica de Negócio para login
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}
	
	public boolean isAlterar() {
		return alterar;
	}
	
}
