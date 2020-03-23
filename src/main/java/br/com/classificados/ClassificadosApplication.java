package br.com.classificados;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.classificados.domain.Categoria;
import br.com.classificados.domain.Produto;
import br.com.classificados.repositories.CategoriaRepository;
import br.com.classificados.repositories.ProdutoRepository;

@SpringBootApplication
public class ClassificadosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClassificadosApplication.class, args);
	}

	@Autowired
	private CategoriaRepository catRep;
	
	@Autowired
	private ProdutoRepository prodRep;
	
    @Override
    public void run(String... args) throws Exception {
        
        Categoria cat1 = new Categoria(null, "Notebooks");
        Categoria cat2 = new Categoria(null, "Vídeo Games");
        
        Produto p1 = new Produto(null, "Notebook Asus Gamer", 2000D);
        Produto p2 = new Produto(null, "PS4", 1200D);
        
        cat1.setProdutos(Arrays.asList(p1));
        cat2.setProdutos(Arrays.asList(p1, p2));
        
        p1.setCategorias(Arrays.asList(cat1, cat2));
        p2.setCategorias(Arrays.asList(cat2));
        
        catRep.saveAll(Arrays.asList(cat1, cat2));
        prodRep.saveAll(Arrays.asList(p1, p2));
        
    }

}
