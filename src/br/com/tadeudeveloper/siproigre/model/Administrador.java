package br.com.tadeudeveloper.siproigre.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Administrador {	
		
	@Id
	@Column(length = 14, nullable = false, unique = true) // 14 caracteres com máscara, campo único
	private String cpf; 	

	@Column(length = 50, nullable = false)
	private String nome;	
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNascimento;	

	@Column(length = 30, nullable = false, unique = true) // campo único
	private String login;	
	
	@Column(length = 8, nullable = false) // máximo até 8 caracteres
	private String senha;	
	
	@Column(length = 16, nullable = false)
	private String telefone;		
	
	// Getters e Setters
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}	
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}	
		
}
