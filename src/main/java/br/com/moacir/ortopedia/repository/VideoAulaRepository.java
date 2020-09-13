/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.repository;

import br.com.moacir.ortopedia.model.Medico;
import br.com.moacir.ortopedia.model.VideoAula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoAulaRepository extends JpaRepository<VideoAula, Long> {

    List<VideoAula> findByTituloIgnoreCaseContains(String titulo);

    List<VideoAula> findByMedicoAndTituloIgnoreCaseContains(Medico medico, String titulo);

}
