package br.com.classificados.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String setor;
    private String telefone;
    
    @Column(unique = true)
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Produto> produtos = new ArrayList<>();

    public Usuario(Integer id, String nome, String setor, String telefone) {
        super();
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.telefone = telefone;
    }

}
