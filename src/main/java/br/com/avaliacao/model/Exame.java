package br.com.avaliacao.model;

public class Exame {
    private int cdExame;
    private String nmExame;
    private int icAtivo;
    private String dsDetalheExame;
    private String dsDetalheExame1;
    
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

}