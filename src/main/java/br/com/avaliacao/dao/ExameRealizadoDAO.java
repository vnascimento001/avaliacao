package br.com.avaliacao.dao;

import br.com.avaliacao.model.Exame;
import br.com.avaliacao.model.ExameRealizado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExameRealizadoDAO {

    // Método para inserir um novo exame realizado
    public void inserirExameRealizado(ExameRealizado exameRealizado) {
        String sql = "INSERT INTO exame_realizado (cd_funcionario, cd_exame, dt_realizacao) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, exameRealizado.getCdFuncionario());
            ps.setInt(2, exameRealizado.getCdExame());
            ps.setDate(3, java.sql.Date.valueOf(exameRealizado.getDtRealizacao()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar exames realizados por um funcionário
    public List<ExameRealizado> buscarExamesRealizadosPorFuncionario(int cdFuncionario) {
        List<ExameRealizado> examesRealizados = new ArrayList<>();
        String sql = "SELECT * FROM exame_realizado WHERE cd_funcionario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cdFuncionario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExameRealizado exameRealizado = new ExameRealizado();
                exameRealizado.setCdFuncionario(rs.getInt("cd_funcionario"));
                exameRealizado.setCdExame(rs.getInt("cd_exame"));
                exameRealizado.setDtRealizacao(rs.getDate("dt_realizacao").toLocalDate().toString());
                examesRealizados.add(exameRealizado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examesRealizados;
    }

    // Método para buscar exames ativos
    public List<Exame> buscarExamesAtivos() {
        List<Exame> examesAtivos = new ArrayList<>();
        String sql = "SELECT * FROM exame WHERE ic_ativo = 1 ORDER BY nm_exame ASC";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exame exame = new Exame();
                exame.setCdExame(rs.getInt("cd_exame"));
                exame.setNmExame(rs.getString("nm_exame"));
                exame.setIcAtivo(rs.getBoolean("ic_ativo"));
                examesAtivos.add(exame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examesAtivos;
    }

}