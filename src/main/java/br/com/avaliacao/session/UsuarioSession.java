package br.com.avaliacao.session;

import java.security.NoSuchAlgorithmException;

import br.com.avaliacao.dao.UsuarioDAO;
import br.com.avaliacao.model.Usuario;

public class UsuarioSession {
    private UsuarioDAO usuarioDAO;

    public UsuarioSession() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario consultarUsuarioPorLogin(String login) {
        return usuarioDAO.consultarUsuarioPorLogin(login);
    }

    public String criptografarSenha(String senha) throws NoSuchAlgorithmException {
        return usuarioDAO.criptografarSenha(senha);
    }

    public void inserirUsuario(Usuario usuario) {
        usuarioDAO.inserirUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.atualizarUsuario(usuario);
    }

    public void deletarUsuario(String login) {
        usuarioDAO.deletarUsuario(login);
    }
}