package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.tadeudeveloper.siproigre.model.Administrador;
import br.com.tadeudeveloper.siproigre.service.AdministradorService;

/**
 * Bean do CRUD de Administradores
 */
@Named("administradores")
@SessionScoped
public class AdministradoresBean extends AbstractBean {	
	
	private static final long serialVersionUID = 1L;

	@Inject
	private AdministradorService administradorService;

	private List<Administrador> administradores;

	private Administrador administrador;

	private boolean alterar = false;	

	/**
	 * Inicia a view de Administradores listando todos os administradores na tabela
	 */	
	@PostConstruct
	public void listar() {
		try {			
			administradores = administradorService.listarAdministradores();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os administradores!");
			erro.printStackTrace();
		}
	}
	
	/**
	 * Prepara o dialog de cadastro de administradores para a inserção de um novo administrador
	 */	
	public void novo() {
		try {
			alterar = false;			
			administrador = new Administrador();		
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar criar um novo administrador!");
			erro.printStackTrace();
		}
	}	
	
	/**
	 * Prepara o dialog de cadastro de administradores para a atualização de um administrador selecionado
	 */	
	public void editar(ActionEvent evento){
		try {
			administrador = (Administrador) evento.getComponent().getAttributes().get("administradorSelecionado");			
			alterar = true;			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar o administrador!");
			erro.printStackTrace();
		}	
	}	
	
	/**
	 * Exclui um administrador selecionado	 
	 */	
	public void excluir(ActionEvent evento) {
		try {
			administrador = (Administrador) evento.getComponent().getAttributes().get("administradorSelecionado");

			administradorService.excluir(administrador.getCpf());			

			administradores = administradorService.listarAdministradores();

			Messages.addGlobalInfo("Administrador removido com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o administrador!");
			erro.printStackTrace();
		}
	}	

	/**
	 * Insere um NOVO ADMINISTRADOR se o atributo alterar estiver setado com valor false
	 * Altera um ADMINISTRADOR EXISTENTE se o atributo alterar estiver setado com valor true
	 */	
	public void salvar() {
		try {			
			if (!alterar) {
				administradorService.inserir(administrador);			
				
				administrador = new Administrador();			

				administradores = administradorService.listarAdministradores();

				Messages.addGlobalInfo("Administrador inserido com sucesso!");
			} else {
				administradorService.alterar(administrador);
				administradores = administradorService.listarAdministradores();
				Messages.addGlobalInfo("Administrador alterado com sucesso!");
			}			
		} catch (RuntimeException erro) {
			administradores = administradorService.listarAdministradores();
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o administrador!");
			erro.printStackTrace();
		}
	}
	
	// Getters e Setters
	public Administrador getAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	public List<Administrador> getAdministradores() {
		return administradores;
	}
	
	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}
	
	public boolean isAlterar() {
		return alterar;
	}
	
	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

}
