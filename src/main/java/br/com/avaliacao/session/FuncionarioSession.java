package br.com.avaliacao.session;

import java.util.List;

import br.com.avaliacao.dao.FuncionarioDAO;
import br.com.avaliacao.model.Funcionario;

public class FuncionarioSession {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioSession() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public List<Funcionario> listarFuncionarios(int pageNumber, int pageSize) {
        return funcionarioDAO.listarFuncionarios(pageNumber, pageSize);
    }

    public void inserirFuncionario(Funcionario funcionario) {
        funcionarioDAO.inserirFuncionario(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        funcionarioDAO.atualizarFuncionario(funcionario);
    }

    public void deletarFuncionario(int cdFuncionario) {
        funcionarioDAO.deletarFuncionario(cdFuncionario);
    }

    public int countTotalFuncionarios() {
        return funcionarioDAO.countTotalFuncionarios();
    }
}