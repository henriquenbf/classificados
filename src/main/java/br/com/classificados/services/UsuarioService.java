package br.com.classificados.services;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.classificados.domain.Usuario;
import br.com.classificados.dto.UsuarioDTO;
import br.com.classificados.exceptions.DataIntegrityException;
import br.com.classificados.exceptions.ObjectNotFoundException;
import br.com.classificados.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario find(Integer id) {
        Optional<Usuario> UsuarioOtp = repository.findById(id);
        return UsuarioOtp.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto com ID = %s não encontrado. Tipo = %s", id, Usuario.class.getName())));
    }

    public Usuario insert(Usuario obj) {
        obj.setId(null);
        validaDadosInsert(obj);
        return repository.save(obj);
    }

    private void validaDadosInsert(Usuario obj) {
        // Deve ser alterado para um ConstraintValidator da entidade Usuario, pois trata-se de uma validação de atributo
        Usuario usuarioComMesmoEmail = repository.findByEmail(obj.getEmail());

        if (nonNull(usuarioComMesmoEmail)) {
//            MensagemCampoInvalido msg = new MensagemCampoInvalido("email", "E-Mail já cadastrado para outro usuário");
            throw new IllegalArgumentException("E-Mail já cadastrado para outro usuário");
        }

    }

    public Usuario update(Usuario obj) {
        Usuario entityDb = find(obj.getId());
        atualizaDadosDaEntidade(entityDb, obj);
        return repository.save(entityDb);
    }

    private void atualizaDadosDaEntidade(Usuario entityDb, Usuario obj) {
        entityDb.setNome(obj.getNome());
        entityDb.setSetor(obj.getSetor());
        entityDb.setTelefone(obj.getTelefone());
        entityDb.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Usuario que possui Usuarios.");
        }
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Usuario fromDTO(UsuarioDTO dto) {
        return new Usuario(dto.getId(), dto.getNome(), dto.getSetor(), dto.getTelefone());
    }

}
