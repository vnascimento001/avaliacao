package br.com.avaliacao.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
	public String execute() throws Exception {
	   	 if (session.get("usuarioLogado") == null) {
			 return LOGIN;
		 }
		return SUCCESS;
	}

}
