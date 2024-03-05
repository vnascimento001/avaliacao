package br.com.avaliacao.action;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.security.auth.login.LoginException;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import br.com.avaliacao.model.Usuario;
import br.com.avaliacao.session.UsuarioSession;

public class UsuarioAction extends ActionSupport implements SessionAware {
    private String login;
    private String senha;
    private UsuarioSession usuarioSession = new UsuarioSession();
    private Map<String, Object> session;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute() {
        if (login == null || login.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            return INPUT;
        }
        try {
            Usuario usuario = usuarioSession.consultarUsuarioPorLogin(login);
            String senhaCriptografada = usuarioSession.criptografarSenha(senha);
            if (usuario != null && usuario.getDsSenha().equals(senhaCriptografada)) {
                session.put("usuarioLogado", usuario);
                return SUCCESS;
            } else {
	                addActionError("Login ou senha incorretos!");
	                return INPUT;
            }
        } catch (NoSuchAlgorithmException e) {
            addActionError("Erro ao processar a senha.");
            return INPUT;
        }
    }

    public String logout() {
        session.remove("usuarioLogado");
        return SUCCESS;
    }
    
    public String isUsuarioLogado() {
    	if (session.get("usuarioLogado") != null) {
    		return SUCCESS;
    	}else {
    		return LOGIN;
    	}
    	
    }

}