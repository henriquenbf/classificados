package br.com.classificados.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.classificados.domain.Produto;
import br.com.classificados.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    
    @Autowired
    private ProdutoService produtoService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok(produto);
    }
    
}
