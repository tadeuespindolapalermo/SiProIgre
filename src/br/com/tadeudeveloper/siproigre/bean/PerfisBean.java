package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.tadeudeveloper.siproigre.model.Perfil;
import br.com.tadeudeveloper.siproigre.service.PerfilService;

/**
 * Bean da tela de Perfis
 */
@Named("perfis")
@SessionScoped
public class PerfisBean extends AbstractBean {
	
	private static final long serialVersionUID = 4200735080578226604L;
	
	@Inject	
	private PerfilService perfilService;

	private List<Perfil> perfis;

	private Perfil perfil;

	private boolean alterar;
	
	/**
	 * Inicia a view de Perfis listando todos os perfis na tabela
	 */	
	@PostConstruct
	public void listar() {
		try {			
			perfis = perfilService.listarPerfis();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os perfis!");
			erro.printStackTrace();
		}
	}
	
	/**
	 * Prepara o dialog de cadastro de perfis para a inserção de um novo perfil
	 */	
	public void novo() {
		try {
			alterar = false;			
			perfil = new Perfil();		
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar criar um novo perfil!");
			erro.printStackTrace();
		}
	}	
	
	/**
	 * Prepara o dialog de cadastro de perfis para a atualização de um perfil selecionado
	 */	
	public void editarPerfil(ActionEvent evento){
		try {
			perfil = (Perfil) evento.getComponent().getAttributes().get("perfilSelecionado");			
			alterar = true;			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um perfil!");
			erro.printStackTrace();
		}	
	}	
	
	/**
	 * Exclui um perfil selecionado
	 */	
	public void excluir(ActionEvent evento) {
		try {
			perfil = (Perfil) evento.getComponent().getAttributes().get("perfilSelecionado");

			perfilService.excluir(perfil.getId());			

			perfis = perfilService.listarPerfis();

			Messages.addGlobalInfo("Perfil removido com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o perfil!");
			erro.printStackTrace();
		}
	}	
	
	/**
	 * Insere um NOVO PERFIL se o atributo alterar estiver setado com valor false
	 * Altera um PERFIL EXISTENTE se o atributo alterar estiver setado com valor true
	 */	
	public void salvar() {
		try {			
			if (!alterar) {
				perfilService.inserir(perfil);			
				
				perfil = new Perfil();			

				perfis = perfilService.listarPerfis();

				Messages.addGlobalInfo("Perfil inserido com sucesso!");
			} else {
				perfilService.alterar(perfil);
				Messages.addGlobalInfo("Perfil alterado com sucesso!");
			}			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o perfil!");
			erro.printStackTrace();
		}
	}	
	
	// Getters e Setters
	public Perfil getPerfil() {
		return perfil;
	}
	
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public List<Perfil> getPerfis() {
		return perfis;
	}
	
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
}
