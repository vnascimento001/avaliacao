package br.com.avaliacao.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.model.Funcionario;

@WebService(endpointInterface = "br.com.avaliacao.soap.FuncionarioWebService")
public class FuncionarioWebServiceImpl implements FuncionarioWebService {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @WebMethod
    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.buscarFuncionariosAtivos();
    }

    @WebMethod
    public void inserirFuncionario(Funcionario funcionario) {
        funcionarioDAO.inserirFuncionario(funcionario);
    }

    @WebMethod
    public void atualizarFuncionario(Funcionario funcionario) {
        funcionarioDAO.atualizarFuncionario(funcionario);
    }

    @WebMethod
    public void deletarFuncionario(int cdFuncionario) {
        funcionarioDAO.deletarFuncionario(cdFuncionario);
    }
}
