package br.com.tadeudeveloper.siproigre.bean;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.tadeudeveloper.siproigre.model.Letra;
import br.com.tadeudeveloper.siproigre.service.LetraService;

/**
 * Bean da tela de cadastro de letras
 */
@Named("letras")
@SessionScoped
public class LetrasBean extends AbstractBean {	
	
	private static final long serialVersionUID = 4728949031792294162L;

	@Inject
	private LetraService letraService;

	private List<Letra> letras;

	private Letra letra;

	private boolean alterar;

	/**
	 * Obtém a lista de letras
	 * @return Lista de letras
	 */
	public List<Letra> getLetras() {
		try {
			if (letras == null) {
				letras = letraService.listarLetras();
			}
			return letras;
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Abre a tela de edição de letra
	 * @param letra Letra a editar
	 * @return
	 */
	public String alterar(Letra letra) {
		this.letra = letra;
		this.alterar = true;
		return "editar_letra";
	}

	/**
	 * Abre a tela de cadastro de letra
	 * @param letra
	 * @return
	 */
	public String novaLetra() {
		letra = new Letra();
		alterar = false;
		return "editar_letra";
	}	
	
	/**
	 * Exclui uma letra
	 * @param letra Letra para excluir
	 * @return
	 */
	public String excluir(Letra letra) {
		try {
			letraService.excluir(letra.getId());
			letras = null;
			
			// Após a exclusão, faz um redirect
			return "listar_letras?faces-redirect=true";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	/**
	 * Cadastra ou atualiza uma letra (depende do estado da flag 'alterar')
	 * @return
	 */
	public String salvar() {
		
		System.out.println(letra.getTitulo());
		try {
			if (alterar) {
				letraService.alterar(letra);
			} else {
				letraService.inserir(letra);
			}
			letra = null;
			letras = null;
			alterar = false;
			return "listar_letras?faces-redirect=true";
		} catch (Exception e) {
			handleException(e);
			return null;
		}
	}

	public Letra getLetra() {
		if (letra == null) {
			letra = new Letra();
		}
		return letra;
	}

	public void setLetra(Letra letra) {
		this.letra = letra;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

}
