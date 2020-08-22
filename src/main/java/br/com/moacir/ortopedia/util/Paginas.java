/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.util;

public enum Paginas {
    USUARIO_EDITA("/pages/usuario/edita.xhtml"),
    USUARIO_LISTA("/pages/usuario/lista.xhtml");

    private final String caminho;

    private Paginas(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }

}
