package br.com.avaliacao.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.avaliacao.model.Usuario;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        try {
			this.connection = Conexao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    
    public String criptografarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(senha.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nm_login, ds_senha, qt_tempo_inatividade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNmLogin());
            // Criptografa a senha antes de inserir
            stmt.setString(2, criptografarSenha(usuario.getDsSenha()));
            stmt.setInt(3, usuario.getQtTempoInatividade());
            stmt.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Usuario consultarUsuarioPorLogin(String login) {
        String sql = "SELECT nm_login, ds_senha, qt_tempo_inatividade FROM usuario WHERE nm_login = ?";
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
            // Criptografa a senha antes de atualizar
            stmt.setString(1, criptografarSenha(usuario.getDsSenha()));
            stmt.setInt(2, usuario.getQtTempoInatividade());
            stmt.setString(3, usuario.getNmLogin());
            stmt.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
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