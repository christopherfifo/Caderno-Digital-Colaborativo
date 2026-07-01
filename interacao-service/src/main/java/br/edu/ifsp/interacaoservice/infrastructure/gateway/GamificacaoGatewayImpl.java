package br.edu.ifsp.interacaoservice.infrastructure.gateway;
import br.edu.ifsp.interacaoservice.application.gateway.GamificacaoGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Component
public class GamificacaoGatewayImpl implements GamificacaoGateway {
    private final RestTemplate restTemplate;
    private final String gamificacaoServiceUrl;

    public GamificacaoGatewayImpl(RestTemplate restTemplate,
                                   @Value("${gamificacao.service.url}") String gamificacaoServiceUrl) {
        this.restTemplate = restTemplate;
        this.gamificacaoServiceUrl = gamificacaoServiceUrl;
    }

    @Override
    public void pontuar(Long usuarioId, String tipoAcao) {
        try {
            String url = gamificacaoServiceUrl + "/api/gamificacao/pontuar";
            Map<String, Object> body = new HashMap<>();
            body.put("usuarioId", usuarioId);
            body.put("tipoAcao", tipoAcao);
            restTemplate.postForObject(url, body, Object.class);
        } catch (Exception ignored) {}
    }
}
