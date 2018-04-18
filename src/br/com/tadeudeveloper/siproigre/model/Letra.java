package br.com.tadeudeveloper.siproigre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Letra {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length = 100, nullable = false)
	private String titulo;
	
	@Column(length = 70, nullable = false)
	private String autor;
	
	@Column(length = 5000, nullable = false)
	private String conteudo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String toString() {
		return "Letra [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", conteudo=" + conteudo + "]";
	}	
	
}
