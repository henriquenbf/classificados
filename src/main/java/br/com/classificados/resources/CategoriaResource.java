package br.com.classificados.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.classificados.domain.Categoria;
import br.com.classificados.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
    @Autowired
    private CategoriaService categoriaService;
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
	    
	    Categoria cat1 = new Categoria(1, "Notebooks");
	    categoriaService.insert(cat1);
	    
	    Categoria entity = categoriaService.find(id);
		return ResponseEntity.ok(entity);
	}
	
}
