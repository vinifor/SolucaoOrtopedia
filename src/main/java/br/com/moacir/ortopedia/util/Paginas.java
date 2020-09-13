/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.util;

public enum Paginas {
    HOME("/pages/index.xhtml"),
    CLIENTE_EDITA("/pages/cliente/edita.xhtml"),
    CLIENTE_LISTA("/pages/cliente/lista.xhtml"),
    CLIENTE_PERFIL("/pages/cliente/perfil.xhtml"),
    MEDICO_EDITA("/pages/medico/edita.xhtml"),
    MEDICO_LISTA("/pages/medico/lista.xhtml"),
    PAGAMENTO_EDITA("/pages/pagamento/edita.xhtml"),
    PAGAMENTO_LISTA("/pages/pagamento/lista.xhtml"),
    USUARIO_EDITA("/pages/usuario/edita.xhtml"),
    USUARIO_LISTA("/pages/usuario/lista.xhtml"),
    VIDEO_AULA_EDITA("/pages/videoaula/edita.xhtml"),
    VIDEO_AULA_LISTA("/pages/videoaula/lista.xhtml"),
    VIDEO_INFORMATIVO_EDITA("/pages/videoinformativo/edita.xhtml"),
    VIDEO_INFORMATIVO_LISTA("/pages/videoinformativo/lista.xhtml");

    private final String caminho;

    private Paginas(String caminho) {
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }

}
