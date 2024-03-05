package br.com.avaliacao.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.*;

import br.com.avaliacao.model.Funcionario;
@Stateless
public class FuncionarioDAO {

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nm_funcionario) VALUES (?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNmFuncionario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir funcionário", e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nm_funcionario = ? WHERE cd_funcionario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNmFuncionario());
            ps.setInt(2, funcionario.getCdFuncionario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário", e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarFuncionario(int cdFuncionario) {
        String sql = "DELETE FROM funcionario WHERE cd_funcionario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cdFuncionario);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar funcionário", e);
        }
    }

    public List<Funcionario> listarFuncionarios(int pageNumber, int pageSize) {
        return listarFuncionariosBase("SELECT cd_funcionario, nm_funcionario FROM funcionario LIMIT ? OFFSET ?", pageNumber, pageSize);
    }

    public List<Funcionario> buscarFuncionariosAtivos() {
        return listarFuncionariosBase("SELECT cd_funcionario, nm_funcionario FROM funcionario", 1, Integer.MAX_VALUE);
    }

    private List<Funcionario> listarFuncionariosBase(String sql, int pageNumber, int pageSize) {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (sql.contains("LIMIT")) {
                ps.setInt(1, pageSize);
                ps.setInt(2, (pageNumber - 1) * pageSize);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCdFuncionario(rs.getInt("cd_funcionario"));
                funcionario.setNmFuncionario(rs.getString("nm_funcionario"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários", e);
        }
        return funcionarios;
    }

    public int countTotalFuncionarios() {
        int totalRecords = 0;
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(cd_funcionario) FROM funcionario")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar total de funcionários", e);
        }
        return totalRecords;
    }
}