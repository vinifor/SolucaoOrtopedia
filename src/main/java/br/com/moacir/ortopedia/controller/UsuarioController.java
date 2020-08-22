/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Usuario;
import br.com.moacir.ortopedia.repository.UsuarioRepository;
import br.com.moacir.ortopedia.util.Paginas;
import br.com.moacir.ortopedia.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "usuarioController")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    private Usuario usuario = null;
    private List<Usuario> usuarios;

    public void abrir() {
        carregar();
        Util.redirect(Paginas.USUARIO_LISTA);
    }

    public void carregar() {
        usuarios = repository.findAll();
    }

    public void novo() {
        usuario = Usuario.builder()
                .build();
        Util.redirect(Paginas.USUARIO_EDITA);
    }

    public void edita(Usuario usuario) {
        this.usuario = usuario;
        Util.redirect(Paginas.USUARIO_EDITA);
    }

    public void salva() {
        repository.save(usuario);
        cancela();
    }

    public void deleta(Usuario usuario) {
        repository.delete(usuario);
        carregar();
    }

    public void cancela() {
        usuario = null;
        carregar();
        Util.redirect(Paginas.USUARIO_LISTA);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
