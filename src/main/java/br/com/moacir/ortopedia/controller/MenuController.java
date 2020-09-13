/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.util.Util;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinif
 */
@Scope(value = "session")
@Component(value = "menuController")
public class MenuController {
    
    public boolean isUsuario(){
        return Util.getGrantedAuthoritys().stream()
                .map(it -> it.toString())
                .anyMatch(it -> it.equals("ROLE_Usuario"));
    }
    
    public boolean isCliente(){
        return Util.getGrantedAuthoritys().stream()
                .map(it -> it.toString())
                .anyMatch(it -> it.equals("ROLE_Cliente"));
    }
}
