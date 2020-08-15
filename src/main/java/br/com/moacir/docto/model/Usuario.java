package br.com.moacir.docto.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Usuario extends Pessoa {

    @Column
    private String funcao;
    @Column
    private String senha;

    @lombok.Builder(builderClassName = "Builder")
    public Usuario(String funcao, String senha, Long id, String name, String cpf, String celular, String email, String endereco, String rg, String sexo) {
        super(id, name, cpf, celular, email, endereco, rg, sexo);
        this.funcao = funcao;
        this.senha = senha;
    }

}
