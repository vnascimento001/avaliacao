package br.com.avaliacao.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.model.Funcionario;
import jakarta.ejb.EJB;

@EJB
public class FuncionarioAction extends ActionSupport {
    private List<Funcionario> funcionarios;
    private int pageNumber;
    private int pageSize = 10;
    private String nmFuncionario;
    private int cdFuncionario;

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public FuncionarioAction() {
    }

    public FuncionarioAction(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public String listarFuncionarios() {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        funcionarios = funcionarioDAO.listarFuncionarios(pageNumber, pageSize);
        calcularPaginas();
        return SUCCESS;
    }

    public String adicionarFuncionario() {
        return SUCCESS;
    }

    public String salvarFuncionario() {
        if (validarNomeFuncionario()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNmFuncionario(nmFuncionario);
            funcionarioDAO.inserirFuncionario(funcionario);
            return SUCCESS;
        }
        return ERROR;
    }

    public String editarFuncionario() {
        return SUCCESS;
    }

    public String atualizarFuncionario() {
        if (validarNomeFuncionario()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCdFuncionario(cdFuncionario);
            funcionario.setNmFuncionario(nmFuncionario);
            funcionarioDAO.atualizarFuncionario(funcionario);
            return SUCCESS;
        }
        return INPUT;
    }

    public String deletarFuncionario() {
        try {
            funcionarioDAO.deletarFuncionario(cdFuncionario);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    private void calcularPaginas() {
        int previousPageNumber = Math.max(1, pageNumber - 1);
        int nextPageNumber = pageNumber + 1;
        if (nextPageNumber > getTotalPages()) {
            nextPageNumber = getTotalPages();
        }
        ActionContext.getContext().put("previousPageNumber", previousPageNumber);
        ActionContext.getContext().put("nextPageNumber", nextPageNumber);
    }
    
    public int getTotalPages() {
        int totalRecords = funcionarioDAO.countTotalFuncionarios();
        return (int) Math.ceil((double) totalRecords / pageSize);
    }
    
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    private boolean validarNomeFuncionario() {
        return nmFuncionario != null && !nmFuncionario.isBlank();
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