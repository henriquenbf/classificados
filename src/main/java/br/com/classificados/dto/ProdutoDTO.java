package br.com.classificados.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.classificados.domain.Categoria;
import br.com.classificados.domain.Produto;
import br.com.classificados.domain.SituacaoProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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
    
    @NotEmpty(message = "O Produto precisa possuir pelo menos uma categoria")
    private List<Integer> categorias;
    
    public ProdutoDTO(Integer id, @NotEmpty(message = "Preenchimento obrigatório") @Length(min = 5, max = 80, message = "Deve possuir entre 5 e 80 caracteres") String nome,
            @NotEmpty(message = "Preenchimento obrigatório") Double valor, @NotEmpty(message = "Preenchimento obrigatório") Integer situacao) {
        super();
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.situacao = situacao;
    }
    
    public Produto toEntity() {
        Produto produto = new Produto(id, nome, valor, SituacaoProduto.getByCodigo(situacao));
        
        categorias.forEach(idCat -> produto.addCategoria(new Categoria(idCat)));
        
        return produto;
    }

    
}
