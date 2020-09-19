/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Cliente;
import br.com.moacir.ortopedia.model.Medico;
import br.com.moacir.ortopedia.model.VideoAula;
import br.com.moacir.ortopedia.repository.ClienteRepository;
import br.com.moacir.ortopedia.repository.MedicoRepository;
import br.com.moacir.ortopedia.repository.PagamentoRepository;
import br.com.moacir.ortopedia.repository.VideoAulaRepository;
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
@Component(value = "homeController")
public class HomeController {

    @Autowired
    private VideoAulaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    private List<VideoAula> videoAulas;
    private VideoAula videoAula;
    private Medico medico;
    private String titulo;

    public void buscaVideoAulas() {
        Cliente cliente = clienteRepository.findByCpf(Util.getUserDetails().getUsername());
        if (cliente != null) {
            if (!pagamentoRepository.findByClienteAndDataPagamentoGreaterThanEqual(cliente, LocalDate.now().minusMonths(1)).isEmpty()) {
                buscar();
            } else {
                Util.showWarnMessage("Conta Suspensa. Realize um novo pagamento", "");
            }
        } else {
            buscar();
        }
    }

    private void buscar() {
        if (medico == null) {
            videoAulas = repository.findByTituloIgnoreCaseContains(titulo);
        } else {
            videoAulas = repository.findByMedicoAndTituloIgnoreCaseContains(medico, titulo);
            medico = null;
        }
    }

    public List<Medico> medicos(String filter) {
        return medicoRepository.findByNomeStartsWithIgnoreCase(filter);
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<VideoAula> getVideoAulas() {
        return videoAulas;
    }

    public void setVideoAulas(List<VideoAula> videoAulas) {
        this.videoAulas = videoAulas;
    }

    public VideoAula getVideoAula() {
        return videoAula;
    }

    public void setVideoAula(VideoAula videoAula) {
        this.videoAula = videoAula;
    }

}
