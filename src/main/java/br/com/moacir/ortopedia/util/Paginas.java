/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.util;

public enum Paginas {
    HOME("/pages/index.xhtml"),
    LOGIN("/login.xhtml"),
    CLIENTE_EDITA("/pages/cliente/edita.xhtml"),
    PAGAMENTO_EDITA("/pages/pagamento/edita.xhtml"),
    USUARIO_EDITA("/pages/usuario/edita.xhtml"),
    USUARIO_LISTA("/pages/usuario/lista.xhtml"),
    VIDEO_EDITA("/pages/video/edita.xhtml"),
    VIDEO_LISTA("/pages/video/lista.xhtml");

    private final String caminho;

    private Paginas(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }

}
