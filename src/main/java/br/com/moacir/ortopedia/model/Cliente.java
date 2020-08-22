package br.com.moacir.ortopedia.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Cliente extends Pessoa {

    @Column
    private String crm;
    @Column
    private Double mensalidade;

    @lombok.Builder(builderClassName = "Builder")
    public Cliente(String crm, Double mensalidade, Long id, String name, String cpf, String celular, String email, String endereco, String rg, String sexo, String senha) {
        super(id, name, cpf, celular, email, endereco, rg, sexo, senha);
        this.crm = crm;
        this.mensalidade = mensalidade;
    }

}
