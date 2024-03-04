package br.com.avaliacao.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import br.com.avaliacao.dao.ExameDAO;
import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.dao.sharedDAO;
import br.com.avaliacao.model.Exame;
import br.com.avaliacao.model.Funcionario;

public class ExameAction extends ActionSupport {

	private List<Exame> exames;

	private int cdExame;
	private String nmExame;
	private int icAtivo;
	private String dsDetalheExame;
	private String dsDetalheExame1;

	private int pageNumber;
	private int pageSize = 10;

	public String exames() {
		return SUCCESS;
	}
	
	public String listarExames() {
	    try {
	        if (pageNumber <= 0) {
	            pageNumber = 1;
	        }
	        ExameDAO exameDAO = new ExameDAO();
	        // Aplicar filtro de ativo
	        String icAtivoStr = ServletActionContext.getRequest().getParameter("icAtivo");
	        Integer icAtivo = null;
	        if (icAtivoStr != null && !icAtivoStr.isEmpty()) {
	            icAtivo = Integer.parseInt(icAtivoStr);
	        }
	        // Aplicar filtro de nome
	        String nmExame = ServletActionContext.getRequest().getParameter("nmExame");
	        exames = exameDAO.listarExames(pageNumber, pageSize, icAtivo, nmExame);

	        // Armazenar parâmetros de filtro no contexto da ação
	        ActionContext.getContext().put("nmExame", nmExame);
	        ActionContext.getContext().put("icAtivo", icAtivo);

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
	    } catch (Exception e) {
	        return ERROR;
	    }
	}

	public String adicionarExame() {
		return SUCCESS;
	}

	public String salvarExame() {
		try {
			String valorSelecionado = ServletActionContext.getRequest().getParameter("icAtivo");
			icAtivo = Integer.parseInt(valorSelecionado);

			ExameDAO exameDAO = new ExameDAO();
			Exame exame = new Exame();
			exame.setNmExame(nmExame);
			exame.setIcAtivo(icAtivo);
			exame.setDsDetalheExame(dsDetalheExame);
			exame.setDsDetalheExame1(dsDetalheExame1);
			exameDAO.inserirExame(exame);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String editarExame() {
		return SUCCESS;
	}

	public String atualizarExame() {
		try {
			ExameDAO exameDAO = new ExameDAO();
			Exame exame = new Exame();
			exame.setNmExame(nmExame);
			exame.setIcAtivo(icAtivo);
			exame.setDsDetalheExame(dsDetalheExame);
			exame.setDsDetalheExame1(dsDetalheExame1);
			exame.setCdExame(cdExame);
			exameDAO.atualizarExame(exame);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}

	}

	public int getTotalPages() {
		ExameDAO exameDAO = new ExameDAO();
	    int totalRecords = exameDAO.countTotalExames(icAtivo, nmExame);
	    return (int) Math.ceil((double) totalRecords / pageSize);
	}
	public List<Exame> getExames() {
		return exames;
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

	public String getNmExame() {
		return nmExame;
	}

	public void setNmExame(String nmExame) {
		this.nmExame = nmExame;
	}

	public int isIcAtivo() {
		return icAtivo;
	}

	public void setIcAtivo(int icAtivo) {
		this.icAtivo = icAtivo;
	}

	public String getDsDetalheExame() {
		return dsDetalheExame;
	}

	public void setDsDetalheExame(String dsDetalheExame) {
		this.dsDetalheExame = dsDetalheExame;
	}

	public String getDsDetalheExame1() {
		return dsDetalheExame1;
	}

	public void setDsDetalheExame1(String dsDetalheExame1) {
		this.dsDetalheExame1 = dsDetalheExame1;
	}

	public Map<String, String> getAtivoOptions() {
		Map<String, String> options = new LinkedHashMap<>();
		options.put("Sim", "1");
		options.put("Não", "0");
		return options;
	}
}
