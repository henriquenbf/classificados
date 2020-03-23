package br.com.classificados.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "Deve possuir entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Double valor;

    @NotEmpty(message = "Preenchimento obrigatório")
    private Integer situacao;

}
