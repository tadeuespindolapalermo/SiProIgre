package br.com.tadeudeveloper.siproigre.bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import org.omnifaces.util.Messages;

/**
 * Bean da tela de login
 */
@Named("login")
@SessionScoped
public class LoginBean extends UsuariosBean {
	
	private static final long serialVersionUID = -2803405610586260650L;	
	
	private boolean logado;
	
	public LoginBean() {
		this.logado = false;
	}		
	
	public boolean isLogado() {
		return logado;
	}
	
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	
	public String logar() {
		if (getUsuario().getLogin().equals("admin") && getUsuario().getSenha().equals("admin")) {
			setLogado(true);
			return "home?faces-redirect=true";
		} else {
			Messages.addGlobalError("Login ou senha inválido(s)!");
			return null;
		}		
	}
	
	public String deslogar(ComponentSystemEvent event) {
		setLogado(false);
		return "login?faces-redirect=true";
	}

}
