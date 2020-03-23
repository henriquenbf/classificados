package br.com.classificados;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.classificados.domain.Categoria;
import br.com.classificados.domain.Produto;
import br.com.classificados.domain.SituacaoProduto;
import br.com.classificados.domain.Usuario;
import br.com.classificados.repositories.CategoriaRepository;
import br.com.classificados.repositories.ProdutoRepository;
import br.com.classificados.repositories.UsuarioRepository;

@SpringBootApplication
public class ClassificadosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClassificadosApplication.class, args);
	}

	@Autowired
	private CategoriaRepository catRep;
	
	@Autowired
	private ProdutoRepository prodRep;
	
	@Autowired
	private UsuarioRepository usuarioRep;
	
    @Override
    public void run(String... args) throws Exception {
        
        Categoria catNotebooks = new Categoria(null, "Notebooks");
        Categoria catVideoGames = new Categoria(null, "Vídeo Games");
        
        Produto prodNoteAsus = new Produto(null, "Notebook Asus Gamer", 2000D, SituacaoProduto.USADO);
        Produto prodPs4 = new Produto(null, "PS4", 1200D, SituacaoProduto.NOVO);
        Produto prodNoteLenovo = new Produto(null, "Notebook Lenovo Ideapad 320", 1000D, SituacaoProduto.USADO);
        
        catNotebooks.setProdutos(Arrays.asList(prodNoteAsus, prodNoteLenovo));
        catVideoGames.setProdutos(Arrays.asList(prodNoteAsus, prodPs4));
        
        prodNoteAsus.setCategorias(Arrays.asList(catNotebooks, catVideoGames));
        prodPs4.setCategorias(Arrays.asList(catVideoGames));
        prodNoteLenovo.setCategorias(Arrays.asList(catNotebooks));
        
        Usuario us1 = new Usuario(null, "Joaozinho", "Desenvolvimento", "94894899");
        Usuario us2 = new Usuario(null, "Pedrinho", "Comercial", "98787879");
        
        us1.setProdutos(Arrays.asList(prodNoteAsus, prodPs4));
        us2.setProdutos(Arrays.asList(prodNoteLenovo));
        prodNoteAsus.setUsuario(us1);
        prodPs4.setUsuario(us1);
        prodNoteLenovo.setUsuario(us2);
        
        usuarioRep.saveAll(Arrays.asList(us1, us2));
        catRep.saveAll(Arrays.asList(catNotebooks, catVideoGames));
        prodRep.saveAll(Arrays.asList(prodNoteAsus, prodPs4, prodNoteLenovo));
        
    }

}
