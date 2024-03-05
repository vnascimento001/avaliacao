package br.com.avaliacao.action;

import java.sql.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import br.com.avaliacao.dao.ExameDAO;
import br.com.avaliacao.dao.ExameRealizadoDAO;
import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.exception.RegraNegocioException;
import br.com.avaliacao.model.Exame;
import br.com.avaliacao.model.ExameRealizado;
import br.com.avaliacao.model.Funcionario;


public class ExameRealizadoAction extends ActionSupport {
	
    private ExameRealizado exameRealizado;
    private List<ExameRealizado> examesRealizados;
	private List<Exame> exames;
	private List<Funcionario> funcionarios;
    private int cdFuncionario;
	private int cdExame;
    private Date dtRealizacao;
    private String nmFuncionario;
    private String nmExame;
    private int pageNumber;
    private int pageSize = 10;
    private Date dtFinal;
    private Date dtInicial;
    
	public String examesRealizados() {
		return SUCCESS;
	}
	
    public String listarExamesRealizados() {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        ExameRealizadoDAO exameRealizadoDAO = new ExameRealizadoDAO();
        examesRealizados = exameRealizadoDAO.listarExamesRealizados(pageNumber, pageSize, cdFuncionario, cdExame);
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarios = funcionarioDAO.buscarFuncionariosAtivos();
        ExameDAO exameDAO = new ExameDAO();
        exames = exameDAO.buscarExamesAtivos();
        ActionContext.getContext().put("exames", exames);
        ActionContext.getContext().put("funcionarios", funcionarios);
        
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
    
    public int getTotalPages() {
    	ExameRealizadoDAO exameRealizadoDAO = new ExameRealizadoDAO();
        int totalRecords = exameRealizadoDAO.countTotalExamesRealizados();
        return (int) Math.ceil((double) totalRecords / pageSize);
    }
    
	public String adicionarExameRealizado() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarios = funcionarioDAO.buscarFuncionariosAtivos();
        ExameDAO exameDAO = new ExameDAO();
        exames = exameDAO.buscarExamesAtivos();
        ActionContext.getContext().put("exames", exames);
        ActionContext.getContext().put("funcionarios", funcionarios);
		return SUCCESS;
	}
	
    public String salvarExameRealizado() throws RegraNegocioException {
    	ExameRealizadoDAO exameRealizadoDAO = new ExameRealizadoDAO();
    	
    	if (exameRealizadoDAO.isExameRealizadoDuplicado(cdFuncionario, cdExame, dtRealizacao)){
    		throw new RegraNegocioException("Este exame já foi realizado por este funcionário na data selecionada.");
    	}
    	exameRealizadoDAO.inserirExameRealizado(cdFuncionario, cdExame, dtRealizacao);
        return SUCCESS;
    }
    
	public String editarExameRealizado() {
		adicionarExameRealizado();
		return SUCCESS;
	}

	public String atualizarExameRealizado() {
		ExameRealizadoDAO exameRealizadoDAO = new ExameRealizadoDAO();
		
		ExameRealizado exame = new ExameRealizado();
		exame.setCdFuncionario(cdFuncionario);
		exame.setCdExame(cdExame);
		exame.setDtRealizacao(dtRealizacao);
		exameRealizadoDAO.atualizarExameRealizado(exame);
		return SUCCESS;
	}
	
	public String deletarExameRealizado() {
        ExameRealizadoDAO exameRealizadoDAO = new ExameRealizadoDAO();
        exameRealizadoDAO.deletarExameRealizado(cdFuncionario, cdExame);
		return SUCCESS;
	}
	
	public String relatorio() {
		return SUCCESS;
	}
	
	public String gerarRelatorio() {
	    ExameRealizadoDAO exameRealizadoDAO = new ExameRealizadoDAO();
	    examesRealizados = exameRealizadoDAO.listarExamesRealizadosPorPeriodo(dtInicial, dtFinal);

	    ActionContext.getContext().put("examesRealizados", examesRealizados);
	    return SUCCESS;
	}
	
    public List<ExameRealizado> getExamesRealizados() {
        return examesRealizados;
    }
    
    public List<Exame> getExame() {
        return exames;
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
	public int getCdExame() {
		return cdExame;
	}

	public void setCdExame(int cdExame) {
		this.cdExame = cdExame;
	}
	public Date getDtRealizacao() {
		return dtRealizacao;
	}
	public void setDtRealizacao(String dtRealizacao) {
		this.dtRealizacao = Date.valueOf(dtRealizacao);
	}
    public String getNmFuncionario() {
        return nmFuncionario;
    }

    public void setNmFuncionario(String nmFuncionario) {
        this.nmFuncionario = nmFuncionario;
    }
    
	public String getNmExame() {
		return nmExame;
	}

	public void setNmExame(String nmExame) {
		this.nmExame = nmExame;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = Date.valueOf(dtFinal);
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = Date.valueOf(dtInicial);
	}

}
