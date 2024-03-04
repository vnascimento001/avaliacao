package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.model.Exame;

public class ExameDAO {

    public List<Exame> listarExamesAtivos() {
        List<Exame> exames = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT nm_exame, ic_ativo, ds_detalhe_exame, ds_detalhe_exame1 FROM exame where ic_ativo = 1;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exame exame = new Exame();
                exame.setCdExame(rs.getInt("cd_exame"));
                exame.setNmExame(rs.getString("nm_exame"));
                exame.setIcAtivo(rs.getBoolean("ic_ativo"));
                exame.setDsDetalheExame(rs.getString("ds_detalhe_exame"));
                exame.setDsDetalheExame1(rs.getString("ds_detalhe_exame1"));
                exames.add(exame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exames;
    }
    
    // Método para inserir um novo exame
    public void inserirExame(Exame exame) {
        String sql = "INSERT INTO exame (nm_exame, ic_ativo, ds_detalhe_exame, ds_detalhe_exame1) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exame.getNmExame());
            ps.setBoolean(2, exame.isIcAtivo());
            ps.setString(3, exame.getDsDetalheExame());
            ps.setString(4, exame.getDsDetalheExame1());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um exame existente
    public void atualizarExame(Exame exame) {
        String sql = "UPDATE exame SET nm_exame = ?, ic_ativo = ?, ds_detalhe_exame = ?, ds_detalhe_exame1 = ? WHERE cd_exame = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exame.getNmExame());
            ps.setBoolean(2, exame.isIcAtivo());
            ps.setString(3, exame.getDsDetalheExame());
            ps.setString(4, exame.getDsDetalheExame1());
            ps.setInt(5, exame.getCdExame());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um exame
    public void deletarExame(int cdExame) {
        String sql = "DELETE FROM exame WHERE cd_exame = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cdExame);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um exame por código
    public Exame buscarExamePorCodigo(int cdExame) {
        Exame exame = null;
        String sql = "SELECT * FROM exame WHERE cd_exame = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cdExame);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exame = new Exame();
                exame.setCdExame(rs.getInt("cd_exame"));
                exame.setNmExame(rs.getString("nm_exame"));
                exame.setIcAtivo(rs.getBoolean("ic_ativo"));
                exame.setDsDetalheExame(rs.getString("ds_detalhe_exame"));
                exame.setDsDetalheExame1(rs.getString("ds_detalhe_exame1"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exame;
    }

}