/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Cliente;
import br.com.moacir.ortopedia.model.Video;
import br.com.moacir.ortopedia.model.Visualizacao;
import br.com.moacir.ortopedia.repository.ClienteRepository;
import br.com.moacir.ortopedia.repository.PagamentoRepository;
import br.com.moacir.ortopedia.repository.VideoRepository;
import br.com.moacir.ortopedia.repository.VisualizacaoRepository;
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
    private VideoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private VisualizacaoRepository visualizacaoRepository;

    private List<Video> videos;
    private Cliente cliente;
    private Video video;
    private String titulo;

    public void encontraCliente() {
        cliente = clienteRepository.findByCpf(Util.getUserDetails().getUsername());
    }

    public void buscaVideoAulas() {
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
        videos = repository.findByTituloIgnoreCaseContains(titulo);
    }

    public void selecionarVideo(Video video) {
        setVideo(video);
        if (visualizacaoRepository.findByClienteAndVideo(cliente, video).isEmpty()) {
            visualizacaoRepository.save(Visualizacao.builder()
                    .cliente(cliente)
                    .video(video)
                    .visualizado(true)
                    .build());
        }
    }

    public boolean isVisualizado(Video video) {
        return !visualizacaoRepository.findByClienteAndVideo(cliente, video).isEmpty();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
