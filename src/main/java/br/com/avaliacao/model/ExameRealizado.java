package br.com.avaliacao.model;

import java.sql.Date;

public class ExameRealizado {
    private int cdFuncionario;
    private String nmFuncionario;
    private String nmExame;
    private int cdExame;
    private Date dtRealizacao;
    
	public int getCdFuncionario() {
		return cdFuncionario;
	}
	public void setCdFuncionario(int cdFuncionario) {
		this.cdFuncionario = cdFuncionario;
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
	public void setDtRealizacao(Date dtRealizacao) {
		this.dtRealizacao = dtRealizacao;
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


}