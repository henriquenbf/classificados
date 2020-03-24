package br.com.classificados.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.classificados.services.validation.UsuarioInsert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@UsuarioInsert
public class UsuarioNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "Deve possuir entre 5 e 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String setor;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email
    private String email;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 20, message = "Deve possuir entre 5 e 20 caracteres")
    private String senha;

}
