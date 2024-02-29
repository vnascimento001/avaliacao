package br.com.avaliacao.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import br.com.avaliacao.dao.UsuarioDAO;
import br.com.avaliacao.model.Usuario;

public class UsuarioAction extends ActionSupport implements SessionAware{
    private String login;
    private String senha;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
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
        Usuario usuario = usuarioDAO.consultarUsuarioPorLogin(login);
        if (usuario != null && usuario.getDsSenha().equals(senha)) {
        	session.put("usuarioLogado", usuario);
            return SUCCESS;
        } else {
            addActionError("Login ou senha incorretos!");
            return INPUT;
        }
    }
    
    public String logout() {
        session.remove("usuarioLogado");
        return SUCCESS;
    }
}
