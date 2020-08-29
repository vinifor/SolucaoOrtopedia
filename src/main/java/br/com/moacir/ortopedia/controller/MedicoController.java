/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Medico;
import br.com.moacir.ortopedia.repository.MedicoRepository;
import br.com.moacir.ortopedia.util.Paginas;
import br.com.moacir.ortopedia.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "medicoController")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    private Medico medico;
    private List<Medico> medicos;

    public void abrir() {
        carregar();
        Util.redirect(Paginas.MEDICO_LISTA);
    }

    public void carregar() {
        medicos = repository.findAll();
    }

    public void novo() {
        medico = Medico.builder()
                .build();
        Util.redirect(Paginas.MEDICO_EDITA);
    }

    public void edita(Medico medico) {
        this.medico = medico;
        Util.redirect(Paginas.MEDICO_EDITA);
    }

    public void salva() {
        repository.save(medico);
        cancela();
    }

    public void deleta(Medico medico) {
        repository.delete(medico);
        carregar();
    }

    public void cancela() {
        medico = null;
        carregar();
        Util.redirect(Paginas.MEDICO_LISTA);
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

}
