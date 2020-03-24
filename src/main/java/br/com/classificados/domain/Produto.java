package br.com.classificados.domain;

import static java.util.Objects.isNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.classificados.dto.ProdutoDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double valor;
    private Integer situacao;
    
    @ManyToMany
    @JoinTable(name = "produtos_categorias", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Produto(Integer id, String nome, Double valor, SituacaoProduto situacao) {
        super();
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.situacao = isNull(situacao) ? null : situacao.codigo;
    }

    public SituacaoProduto getSituacao() {
        return SituacaoProduto.getByCodigo(situacao);
    }

    public void setSituacao(SituacaoProduto situacao) {
        this.situacao = situacao.codigo;
    }
    
    public void addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }
    
    public ProdutoDTO toDto() {
        return new ProdutoDTO(id, nome, valor, situacao);
    }

}
