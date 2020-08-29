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
public class Medico extends Pessoa {

    @Column
    private String qualificacao;
    @Column
    private String crm;

    @lombok.Builder(builderClassName = "Builder")
    public Medico(String qualificacao, String crm, String senha, Long id, String nome, String cpf, String celular, String email, String endereco, String rg, String sexo) {
        super(id, nome, cpf, celular, email, endereco, rg, sexo, senha);
        this.qualificacao = qualificacao;
        this.crm = crm;
    }

}
