package br.com.classificados.domain;

import java.util.stream.Stream;

public enum SituacaoProduto {

    NOVO(1, "Novo"), USADO(2, "Usado");

    public final int codigo;
    public final String descricao;

    private SituacaoProduto(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static SituacaoProduto getByCodigo(int codigo) {
        return Stream.of(values()).filter(t -> codigo == t.codigo).findFirst().orElseThrow(IllegalArgumentException::new);
    }
    
}
