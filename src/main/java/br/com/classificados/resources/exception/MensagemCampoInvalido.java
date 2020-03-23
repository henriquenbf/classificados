package br.com.classificados.resources.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MensagemCampoInvalido implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String campo;
    private String mensagem;
    
}
