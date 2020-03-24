package br.com.classificados.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.classificados.domain.Produto;
import br.com.classificados.dto.ProdutoDTO;
import br.com.classificados.exceptions.ObjectNotFoundException;
import br.com.classificados.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto find(Integer id) {
        Optional<Produto> ProdutoOtp = repository.findById(id);
        return ProdutoOtp.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto com ID = %s não encontrado. Tipo = %s", id, Produto.class.getName())));
    }

    public Produto insert(Produto obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Produto update(Produto obj) {
        Produto entityDb = find(obj.getId());
        atualizaDadosDaEntidade(entityDb, obj);
        return repository.save(entityDb);
    }

    private void atualizaDadosDaEntidade(Produto entityDb, Produto obj) {
        entityDb.setNome(obj.getNome());
        entityDb.setValor(obj.getValor());
        entityDb.setSituacao(obj.getSituacao());
    }

    public void delete(Integer id) {
        find(id);
        repository.deleteById(id);
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = repository.findAll();
        List<ProdutoDTO> produtosDtos = produtos.stream().map(Produto::toDto).collect(Collectors.toList());
        return produtosDtos;
        
    }

    public Page<ProdutoDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest).map(Produto::toDto);
    }

}
