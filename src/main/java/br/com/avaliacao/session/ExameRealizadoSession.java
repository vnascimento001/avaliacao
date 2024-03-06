package br.com.avaliacao.session;

import java.sql.Date;
import java.util.List;

import br.com.avaliacao.dao.ExameDAO;
import br.com.avaliacao.dao.ExameRealizadoDAO;
import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.exception.RegraNegocioException;
import br.com.avaliacao.model.Exame;
import br.com.avaliacao.model.ExameRealizado;
import br.com.avaliacao.model.Funcionario;

public class ExameRealizadoSession {

    private ExameRealizadoDAO exameRealizadoDAO;
    private ExameDAO exameDAO;
    private FuncionarioDAO funcionarioDAO;

    public ExameRealizadoSession() {
        this.exameRealizadoDAO = new ExameRealizadoDAO();
        this.exameDAO = new ExameDAO();
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public List<ExameRealizado> listarExamesRealizados(int pageNumber, int pageSize, int cdFuncionario, int cdExame) {
        return exameRealizadoDAO.listarExamesRealizados(pageNumber, pageSize, cdFuncionario, cdExame);
    }

    public void inserirExameRealizado(int cdFuncionario, int cdExame, Date dtRealizacao) throws RegraNegocioException {
        if (exameRealizadoDAO.isExameRealizadoDuplicado(cdFuncionario, cdExame, dtRealizacao)) {
            throw new RegraNegocioException("Este exame já foi realizado por este funcionário na data selecionada.");
        }
        exameRealizadoDAO.inserirExameRealizado(cdFuncionario, cdExame, dtRealizacao);
    }

    public void atualizarExameRealizado(int cdFuncionario, int cdExame, Date dtRealizacao) {
        ExameRealizado exame = new ExameRealizado();
        exame.setCdFuncionario(cdFuncionario);
        exame.setCdExame(cdExame);
        exame.setDtRealizacao(dtRealizacao);
        exameRealizadoDAO.atualizarExameRealizado(exame);
    }

    public void deletarExameRealizado(int cdFuncionario, int cdExame) {
        exameRealizadoDAO.deletarExameRealizado(cdFuncionario, cdExame);
    }

    public List<Exame> buscarExamesAtivos() {
        return exameDAO.buscarExamesAtivos();
    }

    public List<Funcionario> buscarFuncionariosAtivos() {
        return funcionarioDAO.buscarFuncionariosAtivos();
    }

    public List<ExameRealizado> listarExamesRealizadosPorPeriodo(Date dtInicial, Date dtFinal) {
        return exameRealizadoDAO.listarExamesRealizadosPorPeriodo(dtInicial, dtFinal);
    }

    public int countTotalExamesRealizados() {
        return exameRealizadoDAO.countTotalExamesRealizados();
    }
}