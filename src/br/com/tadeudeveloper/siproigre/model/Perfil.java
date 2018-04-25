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
	@Column(length = 50, nullable = false)
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + ", descrição=" + descricao + "]";
	}
}
