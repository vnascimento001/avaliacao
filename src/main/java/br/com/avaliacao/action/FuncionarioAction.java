package br.com.avaliacao.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.model.Funcionario;

public class FuncionarioAction extends ActionSupport {
	    private List<Funcionario> funcionarios;
	    private String nmFuncionario;
	    private int cdFuncionario;
	    
	    public String listarFuncionarios() {
	        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	        funcionarios = funcionarioDAO.listarFuncionarios();
	        return SUCCESS;
	    }
	    
	    public String adicionarFuncionario() {
	        return SUCCESS;
	    }
	    
	    public String salvarFuncionario() {
	    	if (nmFuncionario.isBlank()){
	    		return ERROR;
	    	}
	        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	        Funcionario funcionario = new Funcionario();
	        funcionario.setNmFuncionario(nmFuncionario);
	        funcionarioDAO.inserirFuncionario(funcionario);
	        return SUCCESS;
	    }
	    
	    public String editarFuncionario() {
	    	return SUCCESS;
	        }
	    
	    public String atualizarFuncionario() {
	    	if (nmFuncionario.isBlank()){
	    		return INPUT;
	    	}
	        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	        Funcionario funcionario = new Funcionario();
	        funcionario.setCdFuncionario(cdFuncionario);
	        funcionario.setNmFuncionario(nmFuncionario);
	        funcionarioDAO.atualizarFuncionario(funcionario);
	        return SUCCESS;
	    }
	    
	    public List<Funcionario> getFuncionarios() {
	        return funcionarios;
	    }
	    
	    public int getCdFuncionario() {
	        return cdFuncionario;
	    }

	    public void setCdFuncionario(int cdFuncionario) {
	        this.cdFuncionario = cdFuncionario;
	    }
	    
	    public String getNmFuncionario() {
	        return nmFuncionario;
	    }

	    public void setNmFuncionario(String nmFuncionario) {
	        this.nmFuncionario = nmFuncionario;
	    }
	    
	    


}