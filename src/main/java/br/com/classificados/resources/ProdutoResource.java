package br.com.classificados.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.classificados.domain.Produto;
import br.com.classificados.dto.ProdutoDTO;
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
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody ProdutoDTO dto) {
        
        Produto entity = produtoService.insert(dto.toEntity());
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ProdutoDTO dto) {
        
        Produto obj = dto.toEntity();
        obj.setId(id);
        produtoService.update(obj);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        return ResponseEntity.ok().body(produtoService.findAll());
    }
    
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page, 
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage, 
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<ProdutoDTO> produtosDtos = produtoService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(produtosDtos);
    }
    
}
