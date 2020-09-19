/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.util;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Util {

    public static void redirect(Paginas paginas) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(paginas.getCaminho());
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showErrorMessage(String message, String detail) {
        showMessage(message, detail, FacesMessage.SEVERITY_ERROR);
    }
    
    public static void showInfoMessage(String message, String detail) {
        showMessage(message, detail, FacesMessage.SEVERITY_INFO);
    }
    
    public static void showWarnMessage(String message, String detail) {
        showMessage(message, detail, FacesMessage.SEVERITY_WARN);
    }

    public static void showMessage(String message, String detail, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, message, detail));
    }

    public static UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Collection<? extends GrantedAuthority> getGrantedAuthoritys() {
        return getUserDetails().getAuthorities();
    }

    public static boolean matchRegex(String regex, String value) {
        return Pattern.compile(regex).matcher(removeMascara(value)).matches();
    }

    public static String removeMascara(String value) {
        return value.replaceAll("\\D", "");
    }
}
