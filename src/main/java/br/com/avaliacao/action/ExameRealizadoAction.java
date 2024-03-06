package br.com.avaliacao.action;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import br.com.avaliacao.exception.RegraNegocioException;
import br.com.avaliacao.model.Exame;
import br.com.avaliacao.model.ExameRealizado;
import br.com.avaliacao.model.Funcionario;
import br.com.avaliacao.session.ExameRealizadoSession;

public class ExameRealizadoAction extends ActionSupport implements SessionAware{

    private ExameRealizadoSession exameRealizadoSession;
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
    
    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public ExameRealizadoAction() {
        this.exameRealizadoSession = new ExameRealizadoSession();
    }

    public String examesRealizados() {
	   	 if (session.get("usuarioLogado") == null) {
			 return LOGIN;
		 }
        return SUCCESS;
    }

    public String listarExamesRealizados() {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        examesRealizados = exameRealizadoSession.listarExamesRealizados(pageNumber, pageSize, cdFuncionario, cdExame);
        funcionarios = exameRealizadoSession.buscarFuncionariosAtivos();
        exames = exameRealizadoSession.buscarExamesAtivos();
        ActionContext.getContext().put("exames", exames);
        ActionContext.getContext().put("funcionarios", funcionarios);
        int previousPageNumber = Math.max(1, pageNumber - 1);
        int nextPageNumber = pageNumber + 1;
        if (nextPageNumber > getTotalPages()) {
            nextPageNumber = getTotalPages();
        }
        ActionContext.getContext().put("previousPageNumber", previousPageNumber);
        ActionContext.getContext().put("nextPageNumber", nextPageNumber);
        return SUCCESS;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) exameRealizadoSession.countTotalExamesRealizados() / pageSize);
    }

    public String adicionarExameRealizado() {
        funcionarios = exameRealizadoSession.buscarFuncionariosAtivos();
        exames = exameRealizadoSession.buscarExamesAtivos();
        ActionContext.getContext().put("exames", exames);
        ActionContext.getContext().put("funcionarios", funcionarios);
        return SUCCESS;
    }

    public String salvarExameRealizado() {
        try {
            exameRealizadoSession.inserirExameRealizado(cdFuncionario, cdExame, dtRealizacao);
        } catch (RegraNegocioException e) {
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String editarExameRealizado() {
	   	 if (session.get("usuarioLogado") == null) {
			 return LOGIN;
		 }
        adicionarExameRealizado();
        return SUCCESS;
    }

    public String atualizarExameRealizado() {
	   	 if (session.get("usuarioLogado") == null) {
			 return LOGIN;
		 }
        exameRealizadoSession.atualizarExameRealizado(cdFuncionario, cdExame, dtRealizacao);
        return SUCCESS;
    }

    public String deletarExameRealizado() {
	   	 if (session.get("usuarioLogado") == null) {
			 return LOGIN;
		 }
        exameRealizadoSession.deletarExameRealizado(cdFuncionario, cdExame);
        return SUCCESS;
    }

    public String relatorio() {
	   	 if (session.get("usuarioLogado") == null) {
			 return LOGIN;
		 }
        return SUCCESS;
    }

    public String gerarRelatorio() {
    	
        examesRealizados = exameRealizadoSession.listarExamesRealizadosPorPeriodo(dtInicial, dtFinal);
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
