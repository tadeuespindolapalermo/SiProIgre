package br.com.tadeudeveloper.siproigre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Perfil {
	
	// Atributos da entidade	
	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true) // campo único
	private Integer id;	
	
	@Column(length = 20, nullable = false, unique = true) // campo único
	private String nome;	
	
	@Column(length = 50, nullable = false)
	private String descricao;
	
	//Getters e Setters
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
	
}
