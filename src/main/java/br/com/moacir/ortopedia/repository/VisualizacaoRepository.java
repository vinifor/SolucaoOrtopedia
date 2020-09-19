/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.repository;

import br.com.moacir.ortopedia.model.Cliente;
import br.com.moacir.ortopedia.model.Video;
import br.com.moacir.ortopedia.model.Visualizacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vinif
 */
public interface VisualizacaoRepository extends JpaRepository<Visualizacao, Long> {

    List<Visualizacao> findByClienteAndVideo(Cliente cliente, Video video);
}
