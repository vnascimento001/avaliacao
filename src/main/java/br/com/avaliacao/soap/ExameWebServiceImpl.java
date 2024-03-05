package br.com.avaliacao.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.avaliacao.dao.ExameDAO;
import br.com.avaliacao.model.Exame;

@WebService(endpointInterface = "br.com.avaliacao.soap.ExameWebService")
public class ExameWebServiceImpl implements ExameWebService {
    private ExameDAO exameDAO = new ExameDAO();

    @WebMethod
    public List<Exame> listarExames() {
        return exameDAO.buscarExamesAtivos();
    }

    @WebMethod
    public void inserirExame(Exame exame) {
        exameDAO.inserirExame(exame);
    }

    @WebMethod
    public void atualizarExame(Exame exame) {
        exameDAO.atualizarExame(exame);
    }

    @WebMethod
    public void deletarExame(int cdExame) {
        exameDAO.deletarExame(cdExame);
    }
}