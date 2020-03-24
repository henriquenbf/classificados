package br.com.classificados.services.validation;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.classificados.domain.Usuario;
import br.com.classificados.dto.UsuarioDTO;
import br.com.classificados.repositories.UsuarioRepository;
import br.com.classificados.resources.exception.MensagemCampoInvalido;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioDTO> {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private UsuarioRepository repo;

    @Override
    public void initialize(UsuarioUpdate ann) {
    }

    @Override
    public boolean isValid(UsuarioDTO dto, ConstraintValidatorContext context) {
        List<MensagemCampoInvalido> camposInvalidos = new ArrayList<>();
        
        // Busca o map de variáveis enviadas na URL da requisição
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));
        
        Usuario usuario = repo.findByEmail(dto.getEmail());
        
        if (nonNull(usuario) && !uriId.equals(usuario.getId())) {
            camposInvalidos.add(new MensagemCampoInvalido("email", "Email já cadastrado"));
        }

        for (MensagemCampoInvalido e : camposInvalidos) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampo()).addConstraintViolation();
        }
        return camposInvalidos.isEmpty();
    }
}