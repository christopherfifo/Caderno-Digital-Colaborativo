package br.edu.ifsp.interacaoservice.infrastructure.gateway;
import br.edu.ifsp.interacaoservice.application.gateway.UsuarioGateway;
import br.edu.ifsp.interacaoservice.exception.RecursoNaoEncontradoException;
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
    private final String midiaServiceUrl;

    public UsuarioGatewayImpl(RestTemplate restTemplate,
                              @Value("${usuario.service.url}") String usuarioServiceUrl,
                              @Value("${midia.service.url}") String midiaServiceUrl) {
        this.restTemplate = restTemplate;
        this.usuarioServiceUrl = usuarioServiceUrl;
        this.midiaServiceUrl = midiaServiceUrl;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String buscarNomeUsuario(Long usuarioId) {
        try {
            String url = usuarioServiceUrl + "/internal/usuarios/" + usuarioId;
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getBody() != null) return (String) response.getBody().get("nome");
        } catch (Exception e) { /* fall through */ }
        return "Usuário Desconhecido";
    }

    @Override
    public void validarUsuarioExistente(Long usuarioId) {
        try {
            String url = usuarioServiceUrl + "/internal/usuarios/" + usuarioId + "/existe";
            restTemplate.getForEntity(url, Boolean.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado.");
        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado.");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long buscarAutorDaMidia(Long midiaId) {
        try {
            String url = midiaServiceUrl + "/internal/midias/" + midiaId;
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getBody() != null) {
                Object autorId = response.getBody().get("autorId");
                if (autorId instanceof Number) return ((Number) autorId).longValue();
            }
        } catch (Exception e) { /* fall through */ }
        return null;
    }
}
