package br.edu.ifsp.midiaservice.infrastructure.gateway;
import br.edu.ifsp.midiaservice.application.gateway.UsuarioGateway;
import br.edu.ifsp.midiaservice.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final RestTemplate restTemplate;
    private final String usuarioServiceUrl;

    public UsuarioGatewayImpl(RestTemplate restTemplate,
                              @Value("${usuario.service.url}") String usuarioServiceUrl) {
        this.restTemplate = restTemplate;
        this.usuarioServiceUrl = usuarioServiceUrl;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String buscarNomeAutor(Long autorId) {
        try {
            String url = usuarioServiceUrl + "/internal/usuarios/" + autorId;
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getBody() != null) return (String) response.getBody().get("nome");
        } catch (Exception e) { /* fall through */ }
        return "Autor Desconhecido";
    }

    @Override
    public void validarAutorExistente(Long autorId) {
        try {
            String url = usuarioServiceUrl + "/internal/usuarios/" + autorId + "/existe";
            restTemplate.getForEntity(url, Boolean.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecursoNaoEncontradoException("Autor não encontrado.");
        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Autor não encontrado.");
        }
    }
}
