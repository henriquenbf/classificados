package br.com.classificados.services.validation;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.classificados.domain.Usuario;
import br.com.classificados.dto.UsuarioNewDTO;
import br.com.classificados.repositories.UsuarioRepository;
import br.com.classificados.resources.exception.MensagemCampoInvalido;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public void initialize(UsuarioInsert ann) {
    }

    @Override
    public boolean isValid(UsuarioNewDTO dto, ConstraintValidatorContext context) {
        List<MensagemCampoInvalido> camposInvalidos = new ArrayList<>();

        Usuario usuario = repo.findByEmail(dto.getEmail());
        
        if (nonNull(usuario)) {
            camposInvalidos.add(new MensagemCampoInvalido("email", "Email já cadastrado"));
        }

        for (MensagemCampoInvalido campoInvalido : camposInvalidos) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(campoInvalido.getMensagem()).addPropertyNode(campoInvalido.getCampo()).addConstraintViolation();
        }
        return camposInvalidos.isEmpty();
    }
}