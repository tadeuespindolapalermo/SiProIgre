package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.tadeudeveloper.siproigre.model.Letra;
import br.com.tadeudeveloper.siproigre.service.LetraService;

/**
 * Bean do CRUD de Letras
 */
@Named("letras")
@SessionScoped
public class LetrasBean extends AbstractBean {	
	
	private static final long serialVersionUID = 4728949031792294162L;

	@Inject
	private LetraService letraService;

	private List<Letra> letras;

	private Letra letra;	
	
	private boolean alterar = false;	
	
	/**
	 * Inicia a view de Letras listando todas as letras na tabela
	 */	
	@PostConstruct
	public void listar() {
		try {			
			letras = letraService.listarLetras();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as letras!");
			erro.printStackTrace();
		}
	}
	
	/**
	 * Prepara o dialog de cadastro de letras para a inserção de uma nova letra
	 */	
	public void novo() {
		try {
			alterar = false;			
			letra = new Letra();		
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar criar uma nova letra!");
			erro.printStackTrace();
		}
	}	
	
	/**
	 * Prepara o dialog de cadastro de letras para a atualização de uma letra selecionada
	 */	
	public void editar(ActionEvent evento){
		try {
			letra = (Letra) evento.getComponent().getAttributes().get("letraSelecionada");			
			alterar = true;			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar a letra!");
			erro.printStackTrace();
		}	
	}	
	
	/**
	 * Exclui uma letra selecionada	 
	 */	
	public void excluir(ActionEvent evento) {
		try {
			letra = (Letra) evento.getComponent().getAttributes().get("letraSelecionada");

			letraService.excluir(letra.getId());			

			letras = letraService.listarLetras();

			Messages.addGlobalInfo("Letra removida com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a letra!");
			erro.printStackTrace();
		}
	}	

	/**
	 * Insere uma NOVA LETRA se o atributo alterar estiver setado com valor false
	 * Altera uma LETRA EXISTENTE se o atributo alterar estiver setado com valor true
	 */	
	public void salvar() {
		try {			
			if (!alterar) {
				letraService.inserir(letra);			
				
				letra = new Letra();			

				letras = letraService.listarLetras();

				Messages.addGlobalInfo("Letra inserida com sucesso!");
			} else {
				letraService.alterar(letra);
				letras = letraService.listarLetras();
				Messages.addGlobalInfo("Letra alterada com sucesso!");
			}			
		} catch (RuntimeException erro) {
			letras = letraService.listarLetras();
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar a letra!");
			erro.printStackTrace();
		}
	}	
	
	// Getters e Setters
	public List<Letra> getLetras() {
		return letras;
	}
	
	public void setLetras(List<Letra> letras) {
		this.letras = letras;
	}

	public Letra getLetra() {		
		return letra;
	}

	public void setLetra(Letra letra) {
		this.letra = letra;
	}			

}
