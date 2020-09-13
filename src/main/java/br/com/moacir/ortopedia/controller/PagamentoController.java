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
@Component(value = "pagamentoController")
public class PagamentoController {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Pagamento pagamento;
    private List<Pagamento> pagamentos;

    public void abrir() {
        carregar();
        Util.redirect(Paginas.PAGAMENTO_LISTA);
    }

    public void carregar() {
        pagamentos = repository.findAll();
        System.out.println(pagamentos);
    }

    public void novo() {
        Cliente cliente = clienteRepository.findByCpf(Util.getUserDetails().getUsername());
        pagamento = Pagamento.builder()
                .cliente(cliente)
                .dataPagamento(LocalDate.now())
                .valor(cliente.getMensalidade())
                .build();
        Util.redirect(Paginas.PAGAMENTO_EDITA);
    }

    public void salva() {
        repository.save(pagamento);
        cancela();
    }

    public void deleta(Pagamento pagamento) {
        repository.delete(pagamento);
        carregar();
    }

    public void cancela() {
        pagamento = null;
        carregar();
        Util.redirect(Paginas.HOME);
    }

    public void encontraBandeira() {
        pagamento.setBandeira(Bandeira.getBandeiraByRegex(pagamento.getNumeroCartao()));
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
