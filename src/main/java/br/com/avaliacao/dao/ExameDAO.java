package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.model.Exame;

public class ExameDAO {

	public List<Exame> listarExames(int pageNumber, int pageSize, Integer icAtivo, String nmExame) {
	    List<Exame> exames = new ArrayList<>();
	    StringBuilder sql = new StringBuilder("SELECT cd_exame, nm_exame, ds_detalhe_exame, ds_detalhe_exame1 FROM exame ");
	    boolean whereAdded = false;

	    if (icAtivo != null) {
	        sql.append(" WHERE ic_ativo = ?");
	        whereAdded = true;
	    }
	    if (nmExame != null && !nmExame.isEmpty()) {
	        if (!whereAdded) {
	            sql.append(" WHERE ");
	            whereAdded = true;
	        } else {
	            sql.append(" AND ");
	        }
	        sql.append(" nm_exame LIKE ?");
	    }
	    sql.append(" ORDER BY nm_exame LIMIT ? OFFSET ?");

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql.toString())) {
	        int index = 1;
	        if (icAtivo != null) {
	            ps.setInt(index++, icAtivo);
	        }
	        if (nmExame != null && !nmExame.isEmpty()) {
	            ps.setString(index++, "%" + nmExame + "%");
	        }
	        ps.setInt(index++, pageSize);
	        ps.setInt(index, (pageNumber - 1) * pageSize);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Exame exame = new Exame();
	            exame.setCdExame(rs.getInt("cd_exame"));
	            exame.setNmExame(rs.getString("nm_exame"));
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
            ps.setInt(2, exame.isIcAtivo());
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
            ps.setInt(2, exame.isIcAtivo());
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
                exame.setIcAtivo(rs.getInt("ic_ativo"));
                exame.setDsDetalheExame(rs.getString("ds_detalhe_exame"));
                exame.setDsDetalheExame1(rs.getString("ds_detalhe_exame1"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exame;
    }
    
    public int countTotalExames(Integer icAtivo, String nmExame) {
        int totalRecords = 0;
        StringBuilder sql = new StringBuilder("SELECT COUNT(nm_exame) FROM exame WHERE 1=1 ");
        boolean hasFilter = false;

        // Adicionar filtro por status ativo/inativo
        if (icAtivo != null) {
            sql.append(" AND ic_ativo = ? ");
            hasFilter = true;
        }

        // Adicionar filtro por nome do exame
        if (nmExame != null && !nmExame.isEmpty()) {
            sql.append(" AND nm_exame LIKE ? ");
            hasFilter = true;
        }

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int index = 1;
            if (hasFilter) {
                if (icAtivo != null) {
                    ps.setInt(index++, icAtivo);
                }
                if (nmExame != null && !nmExame.isEmpty()) {
                    ps.setString(index++, "%" + nmExame + "%");
                }
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRecords;
    }
}