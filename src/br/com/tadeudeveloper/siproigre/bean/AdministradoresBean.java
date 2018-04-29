package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.tadeudeveloper.siproigre.model.Administrador;
import br.com.tadeudeveloper.siproigre.service.AdministradorService;

/**
 * Bean da tela de cadastro de administradores
 */
@Named("administradores")
@SessionScoped
public class AdministradoresBean extends AbstractBean {	
	
	private static final long serialVersionUID = -6593250089911396484L;

	@Inject
	private AdministradorService administradorService;

	private List<Administrador> administradores;

	private Administrador administrador;

	private boolean alterar;	

	/**
	 * Obtém a lista de administradores
	 * @return Lista de administradores
	 */
	public List<Administrador> getAdministradores() {
		try {
			if (administradores == null) {
				administradores = administradorService.listarAdministradores();
			}
			return administradores;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Abre a tela de edição de administrador
	 * @param administrador Administrador a editar
	 * @return
	 */
	public String alterar(Administrador administrador) {
		this.administrador = administrador;
		this.alterar = true;
		return "editar_administrador";
	}

	/**
	 * Abre a tela de cadastro de administrador
	 * @param administrador
	 * @return
	 */
	public String novoAdministrador() {
		administrador = new Administrador();
		alterar = false;
		return "editar_administrador";
	}	
	
	/**
	 * Exclui um administrador
	 * @param administrador Administrador para excluir
	 * @return
	 */
	public String excluir(Administrador administrador) {
		try {
			administradorService.excluir(administrador.getCpf());
			administradores = null;
			
			// Após a exclusão, faz um redirect
			return "listar_administradores?faces-redirect=true";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Cadastra ou atualiza um administrador (depende do estado da flag 'alterar')
	 * @return
	 */
	public String salvar() {		
		System.out.println(administrador.getDataNascimento());
		try {
			if (alterar) {
				administradorService.alterar(administrador);
			} else {
				administradorService.inserir(administrador);
			}
			administrador = null;
			administradores = null;
			alterar = false;
			return "listar_administradores?faces-redirect=true";			
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public Administrador getAdministrador() {
		if (administrador == null) {
			administrador = new Administrador();
		}
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}	

}
