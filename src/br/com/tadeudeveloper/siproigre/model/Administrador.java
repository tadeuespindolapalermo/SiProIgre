package br.com.tadeudeveloper.siproigre.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Administrador {	

	/**
	 * CPF do administrador
	 */	
	@Id
	@Column(length = 14, nullable = false, unique = true) // 14 caracteres com máscara
	private String cpf; 
	
	/**
	 * Nome do administrador
	 */
	@Column(length = 50, nullable = false)
	private String nome;
	
	/**
	 * Data de nascimento
	 */
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNascimento;
	

	/**
	 * Nome do usuário para login
	 */
	@Column(length = 30, nullable = false, unique = true)
	private String login;	

	/**
	 * Senha do administrador
	 */
	@Column(length = 8, nullable = false) // apenas 8 caracteres
	private String senha;
	
	/**
	 * Telefone do administrador
	 */
	@Column(length = 16, nullable = false)
	private String telefone;		
	

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

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String dataNascStr = dataNascimento != null ? sdf.format(dataNascimento) : null;
		
		return "Usuario [cpf=" + cpf + ", nome=" + nome + ", dataNascimento=" + dataNascStr + "]";
	}
}
