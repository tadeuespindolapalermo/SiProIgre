package br.com.tadeudeveloper.siproigre.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

	/**
	 * CPF
	 */
	@Id
	@Column(length = 14)
	private String cpf; 
	
	/**
	 * Nome do usuário
	 */
	@Column(length = 50, nullable = false)
	private String nome;
	
	/**
	 * Data de nascimento
	 */
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	

	/**
	 * Nome do usuário para login
	 */
	@Column(length = 50, nullable = false)
	private String login;
	

	/**
	 * Senha do usuário
	 */
	@Column(length = 50, nullable = false)
	private String senha;
	
	/**
	 * ID do perfil onde o usuário está associado
	 */
	@ManyToOne
	private Perfil perfil;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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
