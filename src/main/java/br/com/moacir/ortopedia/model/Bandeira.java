/*
 * Copyright 2020 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.moacir.ortopedia.model;

import static br.com.moacir.ortopedia.util.Util.matchRegex;

public enum Bandeira {

    VISA("VISA", "visa.png", true, "4[0-9]{12}(?:[0-9]{3})"),
    MASTER_CARD("MASTER CARD", "master-card.png", true, "5[1-5][0-9]{14}"),
    AMEX("AMEX", "amex.png", true, "3[47][0-9]{13}"),
    ELO("ELO", "elo.png", true, "((((636368)|(438935)|(504175)|(451416)|(636297))\\d{0,10})|((5067)|(4576)|(4011))\\d{0,12})"),
    HIPERCARD("HIPERCARD", "hipercard.png", true, "(606282\\d{10}(\\d{3})?)|(3841\\d{15})"),
    DINERS("DINERS", "diners.png", true, "3(?:0[0-5]|[68][0-9])[0-9]{11}");

    private final String nome;
    private final String imagem;
    private final Boolean formaPagamento;
    private final String regex;

    private Bandeira(String nome, String imagem, Boolean formaPagamento, String regex) {
        this.nome = nome;
        this.imagem = imagem;
        this.formaPagamento = formaPagamento;
        this.regex = regex;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public Boolean getFormaPagamento() {
        return formaPagamento;
    }

    public String getRegex() {
        return regex;
    }

    public static Bandeira getBandeiraByRegex(String numero) {
        if (matchRegex(VISA.getRegex(), numero)) {
            return VISA;
        } else if (matchRegex(MASTER_CARD.getRegex(), numero)) {
            return MASTER_CARD;
        } else if (matchRegex(ELO.getRegex(), numero)) {
            return ELO;
        } else if (matchRegex(HIPERCARD.getRegex(), numero)) {
            return HIPERCARD;
        } else if (matchRegex(DINERS.getRegex(), numero)) {
            return DINERS;
        } else if (matchRegex(AMEX.getRegex(), numero)) {
            return AMEX;
        } else {
            return null;
        }
    }

}
