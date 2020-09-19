/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Bandeira;
import br.com.moacir.ortopedia.model.Cliente;
import br.com.moacir.ortopedia.model.Pagamento;
import br.com.moacir.ortopedia.repository.ClienteRepository;
import br.com.moacir.ortopedia.repository.PagamentoRepository;
import br.com.moacir.ortopedia.util.Paginas;
import br.com.moacir.ortopedia.util.Util;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinif
 */
@Scope(value = "session")
@Component(value = "landingController")
public class LandingController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    private Cliente cliente;
    private Pagamento pagamento;

    public void novo() {
        cliente = Cliente.builder()
                .mensalidade(100.0)
                .build();
        pagamento = Pagamento.builder()
                .cliente(cliente)
                .dataPagamento(LocalDate.now())
                .valor(cliente.getMensalidade())
                .build();
    }

    public void salva() {
        clienteRepository.save(cliente);
        pagamentoRepository.save(pagamento);
        cancela();
        Util.redirect(Paginas.LOGIN);
    }

    public void cancela() {
        cliente = null;
        pagamento = null;
    }

    public void encontraBandeira() {
        pagamento.setBandeira(Bandeira.getBandeiraByRegex(pagamento.getNumeroCartao()));
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

}
