package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sharedDAO {

	public int countTotalRegistros(String campo, String tabela, Integer icAtivo) {
	    int totalRecords = 0;
	    StringBuilder sql = new StringBuilder("SELECT COUNT(" + campo + ") FROM " + tabela);
	    if (campo != null && !campo.isEmpty()) {
	        sql.append( campo + " LIKE ? ");
	    }
	    if (icAtivo != null) {
	        if (!sql.toString().contains("WHERE")) {
	            sql.append("WHERE ");
	        } else {
	            sql.append("AND ");
	        }
	        sql.append("ic_ativo = ? ");
	    }
	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql.toString())) {
	        int index = 1;
	        if (campo != null && !campo.isEmpty()) {
	            ps.setString(index++, "%" + campo + "%");
	        }
	        if (icAtivo != null) {
	            ps.setInt(index++, icAtivo);
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
