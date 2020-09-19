/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Cliente;
import br.com.moacir.ortopedia.repository.ClienteRepository;
import br.com.moacir.ortopedia.util.Paginas;
import br.com.moacir.ortopedia.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "clienteController")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    private Cliente cliente;
    private List<Cliente> clientes;

    public void salva() {
        repository.save(cliente);
        cancela();
    }

    public void cancela() {
        cliente = null;
        Util.redirect(Paginas.HOME);
    }

    public void editaPerfil() {
        cliente = repository.findByCpf(Util.getUserDetails().getUsername());
        Util.redirect(Paginas.CLIENTE_EDITA);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
