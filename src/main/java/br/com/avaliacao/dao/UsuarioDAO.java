package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.model.Usuario;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        try {
            // Substitua os valores abaixo pelos detalhes da sua conexão
            String url = "jdbc:mysql://localhost:3306/avaliacao";
            String user = "root";
            String password = "12345";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nm_login, ds_senha, qt_tempo_inatividade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNmLogin());
            stmt.setString(2, usuario.getDsSenha());
            stmt.setInt(3, usuario.getQtTempoInatividade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario consultarUsuarioPorLogin(String login) {
        String sql = "SELECT * FROM usuario WHERE nm_login = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNmLogin(rs.getString("nm_login"));
                usuario.setDsSenha(rs.getString("ds_senha"));
                usuario.setQtTempoInatividade(rs.getInt("qt_tempo_inatividade"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET ds_senha = ?, qt_tempo_inatividade = ? WHERE nm_login = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getDsSenha());
            stmt.setInt(2, usuario.getQtTempoInatividade());
            stmt.setString(3, usuario.getNmLogin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuario(String login) {
        String sql = "DELETE FROM usuario WHERE nm_login = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}