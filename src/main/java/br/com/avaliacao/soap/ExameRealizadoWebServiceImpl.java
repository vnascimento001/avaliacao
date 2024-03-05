package br.com.avaliacao.soap;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.avaliacao.dao.ExameRealizadoDAO;
import br.com.avaliacao.model.ExameRealizado;

@WebService(endpointInterface = "br.com.avaliacao.soap.ExameRealizadoWebService")
public class ExameRealizadoWebServiceImpl implements ExameRealizadoWebService {
    private ExameRealizadoDAO exameRealizadoDAO = new ExameRealizadoDAO();

    @WebMethod
    public List<ExameRealizado> listarExamesRealizados() {
        // Exemplo de chamada com parâmetros de paginação e filtros
        return exameRealizadoDAO.listarExamesRealizados(1, Integer.MAX_VALUE, 0, 0);
    }

    @WebMethod
    public void inserirExameRealizado(int cdFuncionario, int cdExame, java.sql.Date dtRealizacao) {
        exameRealizadoDAO.inserirExameRealizado(cdFuncionario, cdExame, dtRealizacao);
    }

    @WebMethod
    public void deletarExameRealizado(int cdFuncionario, int cdExame) {
        exameRealizadoDAO.deletarExameRealizado(cdFuncionario, cdExame);
    }
}
