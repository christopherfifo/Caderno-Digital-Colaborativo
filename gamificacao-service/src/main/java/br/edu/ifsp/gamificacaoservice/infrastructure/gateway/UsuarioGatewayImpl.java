package br.edu.ifsp.gamificacaoservice.infrastructure.gateway;
import br.edu.ifsp.gamificacaoservice.application.gateway.UsuarioGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
    public String buscarNomeUsuario(Long usuarioId) {
        try {
            String url = usuarioServiceUrl + "/internal/usuarios/" + usuarioId;
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getBody() != null) return (String) response.getBody().get("nome");
        } catch (Exception e) { /* fall through */ }
        return "Usuário Desconhecido";
    }
}
