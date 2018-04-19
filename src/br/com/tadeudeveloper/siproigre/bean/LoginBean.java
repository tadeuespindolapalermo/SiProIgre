package br.com.tadeudeveloper.siproigre.bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

/**
 * Bean da tela de login
 */
@Named("login")
@SessionScoped
public class LoginBean extends UsuariosBean {
	
	private static final long serialVersionUID = -2803405610586260650L;		
	
	public String logar() {
		if (getUsuario().getLogin().equals("aaa") && getUsuario().getSenha().equals("123")) {			
			return "home?faces-redirect=true";
		} else {
			Messages.addGlobalError("Login ou senha inválido(s)!");
			return null;
		}		
	}

}
