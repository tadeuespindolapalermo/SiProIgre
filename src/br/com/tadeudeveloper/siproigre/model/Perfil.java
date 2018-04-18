package br.com.tadeudeveloper.siproigre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Perfil {

	/**
	 * ID do perfil
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Nome do perfil
	 */
	@Column(length = 20, nullable = false)
	private String nome;
	
	/**
	 * Nível de acesso que o perfil considera
	 */
	@Column(nullable = false)
	private Integer nivelAcesso;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(Integer nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + ", nivelAcesso=" + nivelAcesso + "]";
	}
}
