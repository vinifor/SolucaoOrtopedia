/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.repository;

import br.com.moacir.ortopedia.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vinif
 */
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
