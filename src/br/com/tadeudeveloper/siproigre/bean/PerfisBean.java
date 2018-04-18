package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tadeudeveloper.siproigre.model.Perfil;
import br.com.tadeudeveloper.siproigre.service.PerfilService;

/**
 * Bean da tela de cadastro de perfils
 */
@Named("perfis")
@SessionScoped
public class PerfisBean extends AbstractBean {
	
	private static final long serialVersionUID = 4200735080578226604L;
	
	@Inject	
	private PerfilService perfilService;

	private List<Perfil> perfils;

	private Perfil perfil;

	private boolean alterar;

	/**
	 * Obtém a lista de perfils
	 * @return Lista de perfils
	 */
	public List<Perfil> getPerfis() {
		try {
			if (perfils == null) {
				perfils = perfilService.listarPerfis();
			}
			return perfils;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Abre a tela de edição de perfil
	 * @param perfil Perfil a editar
	 * @return
	 */
	public String alterar(Perfil perfil) {
		this.perfil = perfil;
		this.alterar = true;
		return "editar_perfil";
	}
	
	/**
	 * Abre a tela de cadastro de perfil
	 * @return
	 */
	public String novoPerfil() {
		perfil = new Perfil();
		alterar = false;
		return "editar_perfil";
	}

	/**
	 * Exclui uma perfil
	 * @param perfil Perfil para excluir
	 * @return
	 */
	public String excluir(Perfil perfil) {
		try {
			perfilService.excluir(perfil.getId());
			perfils = null;
			
			// Após a exclusão, faz um redirect
			return "listar_perfis?faces-redirect=true";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Cadastra ou atualiza uma perfil (depende do estado da flag 'alterar')
	 * @return
	 */
	public String salvar() {
		try {
			if (alterar) {
				perfilService.alterar(perfil);
			} else {
				perfilService.inserir(perfil);
			}
			perfil = null;
			perfils = null;
			alterar = false;
			return "listar_perfis?faces-redirect=true";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public Perfil getPerfil() {
		if (perfil == null) {
			perfil = new Perfil();
		}
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

}
