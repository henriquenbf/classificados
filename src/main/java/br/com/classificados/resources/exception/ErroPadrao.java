package br.com.classificados.resources.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErroPadrao implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer status;
    private String msg;
    private long timestamp;
    
}
