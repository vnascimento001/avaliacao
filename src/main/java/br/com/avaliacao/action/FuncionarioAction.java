package br.com.avaliacao.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.model.Funcionario;

public class FuncionarioAction extends ActionSupport {
	    private List<Funcionario> funcionarios;
	    private int pageNumber;
	    private int pageSize = 10;
	    private String nmFuncionario;
	    private int cdFuncionario;
	    
	    public String listarFuncionarios() {
	        if (pageNumber <= 0) {
	            pageNumber = 1;
	        }
	        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	        funcionarios = funcionarioDAO.listarFuncionarios(pageNumber, pageSize);
	        
	        // Calcular previousPageNumber e nextPageNumber
	        int previousPageNumber = Math.max(1, pageNumber - 1);
	        int nextPageNumber = pageNumber + 1;
	        
	        // Verificar se a próxima página é válida
	        if (nextPageNumber > getTotalPages()) {
	            nextPageNumber = getTotalPages(); // Redirecionar para a última página válida
	        }
	        // Adicionar os valores calculados ao contexto do JSP
	        ActionContext.getContext().put("previousPageNumber", previousPageNumber);
	        ActionContext.getContext().put("nextPageNumber", nextPageNumber);
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
	    
	    public String deletarFuncionario() {
	    	try {
		        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		        funcionarioDAO.deletarFuncionario(cdFuncionario);
		        return SUCCESS;
	    	}catch (Exception e){
	    		return ERROR;
	    	}
	    }
	    
	    public int getTotalPages() {
	        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	        int totalRecords = funcionarioDAO.countTotalFuncionarios();
	        return (int) Math.ceil((double) totalRecords / pageSize);
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
	    
	    
	    public void setPageNumber(int pageNumber) {
	        this.pageNumber = pageNumber;
	    }

	    public void setPageSize(int pageSize) {
	        this.pageSize = pageSize;
	    }
	    
	    public int getPageNumber(int pageNumber) {
	        return pageNumber;
	    }

	    public int getPageSize(int pageSize) {
	        return pageSize;
	    }  

}