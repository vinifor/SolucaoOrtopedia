/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.validator;

import static br.com.moacir.ortopedia.util.Util.removeMascara;
import java.util.InputMismatchException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinif
 */
@FacesValidator(value = "CPFValidator")
@Component(value = "CPFValidator")
public class CPFValidator implements Validator<String> {

    private final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido", "");

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        String cpf = removeMascara(value);
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            throw new ValidatorException(message);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;
        try {

            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if (dig10 != cpf.charAt(9) || dig11 != cpf.charAt(10)) {
                throw new ValidatorException(message);
            }
        } catch (InputMismatchException erro) {
            throw new ValidatorException(message);
        }
    }

}
