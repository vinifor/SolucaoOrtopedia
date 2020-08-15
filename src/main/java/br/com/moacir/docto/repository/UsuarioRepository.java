/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.docto.repository;

import br.com.moacir.docto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vinif
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
