package br.com.classificados.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacao extends ErroPadrao {

    private static final long serialVersionUID = 1L;
    
    private List<MensagemCampoInvalido> msgCamposInvalidos = new ArrayList<>();
    
    public ErroDeValidacao(Integer status, String msg, long timestamp) {
        super(status, msg, timestamp);
    }
    
    public void addMensagemCampo(String campo, String msg) {
        msgCamposInvalidos.add(new MensagemCampoInvalido(campo, msg));
    }

    public List<MensagemCampoInvalido> getMsgCamposInvalidos() {
        return msgCamposInvalidos;
    }
    
}
