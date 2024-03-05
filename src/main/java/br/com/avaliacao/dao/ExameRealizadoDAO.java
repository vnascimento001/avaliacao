package br.com.avaliacao.dao;

import br.com.avaliacao.model.ExameRealizado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExameRealizadoDAO {

	public List<ExameRealizado> listarExamesRealizados(int pageNumber, int pageSize, int cdFuncionario, int cdExame) {
	    List<ExameRealizado> examesRealizados = new ArrayList<>();
	    String sql = "SELECT exame_realizado.*, exame.nm_exame, funcionario.nm_funcionario " +
	                 "FROM exame_realizado " +
	                 "JOIN exame ON exame_realizado.cd_exame = exame.cd_exame " +
	                 "JOIN funcionario ON exame_realizado.cd_funcionario = funcionario.cd_funcionario " +
	                 "WHERE exame.ic_ativo = 1 ";
	    
	    // Adiciona condições de filtragem se os valores não forem vazios
	    if (cdFuncionario > 0) {
	        sql += "AND exame_realizado.cd_funcionario = ? ";
	    }
	    if (cdExame > 0) {
	        sql += "AND exame_realizado.cd_exame = ? ";
	    }
	    
	    sql += "ORDER BY funcionario.nm_funcionario ASC, exame.nm_exame ASC " +
	           "LIMIT ? OFFSET ?";
	    
	    try {
	        Connection conn = Conexao.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        int index = 1;
	        if (cdFuncionario > 0) {
	            stmt.setInt(index++, cdFuncionario);
	        }
	        if (cdExame > 0) {
	            stmt.setInt(index++, cdExame);
	        }
	        stmt.setInt(index++, pageSize);
	        stmt.setInt(index, (pageNumber - 1) * pageSize);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            ExameRealizado exameRealizado = new ExameRealizado();
	            exameRealizado.setCdFuncionario(rs.getInt("cd_funcionario"));
	            exameRealizado.setCdExame(rs.getInt("cd_exame"));
	            exameRealizado.setNmExame(rs.getString("nm_exame"));
	            exameRealizado.setDtRealizacao(rs.getDate("dt_realizacao"));
	            exameRealizado.setNmFuncionario(rs.getString("nm_funcionario"));
	            examesRealizados.add(exameRealizado);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return examesRealizados;
	}
	
    // Método para inserir um novo exame realizado
    public void inserirExameRealizado(int cdFuncionario, int cdExame, Date dtRealizacao) {
        String sql = "INSERT INTO exame_realizado (cd_funcionario, cd_exame, dt_realizacao) VALUES (?, ?, ?)";
        try {
        	Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cdFuncionario);
            stmt.setInt(2, cdExame);
            stmt.setDate(3, dtRealizacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isExameRealizadoDuplicado(int cdFuncionario, int cdExame, Date dtRealizacao) {
        String sqlCheck = "SELECT COUNT(*) FROM exame_realizado WHERE cd_funcionario = ? AND cd_exame = ? AND dt_realizacao = ?";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck);
            stmtCheck.setInt(1, cdFuncionario);
            stmtCheck.setInt(2, cdExame);
            stmtCheck.setDate(3, dtRealizacao);
            ResultSet rsCheck = stmtCheck.executeQuery();
            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Se não existir um exame realizado com os mesmos parâmetros, retorna false
        return false;
    }
    // Método para atualizar um exame realizado existente
    public void atualizarExameRealizado(ExameRealizado exame) {
        String sql = "UPDATE exame_realizado SET cd_funcionario = ?, cd_exame = ?, dt_realizacao = ? WHERE cd_funcionario = ? AND cd_exame = ? AND dt_realizacao = ?";
        try {
        	Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, exame.getCdFuncionario());
            stmt.setInt(2, exame.getCdExame());
            stmt.setDate(3, exame.getDtRealizacao());
            stmt.setInt(4, exame.getCdFuncionario());
            stmt.setInt(5, exame.getCdExame());
            stmt.setDate(6, exame.getDtRealizacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um exame realizado
    public void deletarExameRealizado(int cdFuncionario, int cdExame) {
        String sql = "DELETE FROM exame_realizado WHERE cd_funcionario = ? AND cd_exame = ?";
        try {
        	Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cdFuncionario);
            stmt.setInt(2, cdExame);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<ExameRealizado> listarExamesRealizadosPorPeriodo(Date dtInicial, Date dtFinal) {
        List<ExameRealizado> examesRealizados = new ArrayList<>();
        String sql = "SELECT exame_realizado.*, exame.nm_exame, funcionario.nm_funcionario " +
                     "FROM exame_realizado " +
                     "JOIN exame ON exame_realizado.cd_exame = exame.cd_exame " +
                     "JOIN funcionario ON exame_realizado.cd_funcionario = funcionario.cd_funcionario " +
                     "WHERE exame_realizado.dt_realizacao BETWEEN ? AND ?";

        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, dtInicial);
            stmt.setDate(2, dtFinal);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ExameRealizado exameRealizado = new ExameRealizado();
                exameRealizado.setCdFuncionario(rs.getInt("cd_funcionario"));
                exameRealizado.setCdExame(rs.getInt("cd_exame"));
                exameRealizado.setNmExame(rs.getString("nm_exame"));
                exameRealizado.setDtRealizacao(rs.getDate("dt_realizacao"));
                exameRealizado.setNmFuncionario(rs.getString("nm_funcionario"));
                examesRealizados.add(exameRealizado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examesRealizados;
    }
    
    public int countTotalExamesRealizados() {
        int totalRecords = 0;
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(cd_funcionario) FROM exame_realizado")) {
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