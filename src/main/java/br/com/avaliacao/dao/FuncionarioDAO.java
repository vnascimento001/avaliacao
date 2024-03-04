package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.model.Funcionario;

public class FuncionarioDAO {
	
	
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT cd_funcionario, nm_funcionario FROM funcionario")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCdFuncionario(rs.getInt("cd_funcionario"));
                funcionario.setNmFuncionario(rs.getString("nm_funcionario"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public Funcionario buscarFuncionarioPorCodigo(int cdFuncionario) {
    	Funcionario funcionario = null;
        String sql = "SELECT cd_funcionario, nm_funcionario FROM funcionario WHERE cd_funcionario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cdFuncionario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	funcionario = new Funcionario();
            	funcionario.setCdFuncionario(rs.getInt("cd_funcionario"));
            	funcionario.setNmFuncionario(rs.getString("nm_funcionario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }


    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nm_funcionario) VALUES (?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNmFuncionario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nm_funcionario = ? WHERE cd_funcionario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNmFuncionario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarFuncionario(int cdFuncionario) {
        String sql = "DELETE FROM funcionario WHERE cd_funcionario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cdFuncionario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
