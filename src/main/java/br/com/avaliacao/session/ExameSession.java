package br.com.avaliacao.session;

import java.util.List;
import br.com.avaliacao.dao.ExameDAO;
import br.com.avaliacao.model.Exame;

public class ExameSession {
    private ExameDAO exameDAO;

    public ExameSession() {
        this.exameDAO = new ExameDAO();
    }

    public List<Exame> listarExames(int pageNumber, int pageSize, Integer icAtivo, String nmExame) {
        return exameDAO.listarExames(pageNumber, pageSize, icAtivo, nmExame);
    }

    public void inserirExame(Exame exame) {
        exameDAO.inserirExame(exame);
    }

    public void atualizarExame(Exame exame) {
        exameDAO.atualizarExame(exame);
    }

    public void deletarExame(int cdExame) {
        exameDAO.deletarExame(cdExame);
    }

    public List<Exame> buscarExamesAtivos() {
        return exameDAO.buscarExamesAtivos();
    }

    public int countTotalExames(Integer icAtivo, String nmExame) {
        return exameDAO.countTotalExames(icAtivo, nmExame);
    }

    public boolean exameFoiRealizado(int cdExame) {
        return exameDAO.exameFoiRealizado(cdExame);
    }
}