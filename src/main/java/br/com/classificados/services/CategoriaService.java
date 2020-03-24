package br.com.classificados.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.classificados.domain.Categoria;
import br.com.classificados.dto.CategoriaDTO;
import br.com.classificados.exceptions.DataIntegrityException;
import br.com.classificados.exceptions.ObjectNotFoundException;
import br.com.classificados.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoriaOtp = repository.findById(id);
        return categoriaOtp.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto com ID = %s não encontrado. Tipo = %s", id, Categoria.class.getName())));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria entityDb = find(obj.getId());
        atualizaDadosDaEntidade(entityDb, obj);
        return repository.save(entityDb);
    }

    private void atualizaDadosDaEntidade(Categoria entityDb, Categoria obj) {
        entityDb.setNome(obj.getNome());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui Produtos.");
        }
    }

    public List<CategoriaDTO> findAll() {
        return repository.findAll().stream().map(Categoria::toDto).collect(Collectors.toList());
    }

    public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest).map(Categoria::toDto);
    }

}
