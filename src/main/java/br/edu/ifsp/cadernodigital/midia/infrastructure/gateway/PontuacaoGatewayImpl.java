package br.edu.ifsp.cadernodigital.midia.infrastructure.gateway;

import br.edu.ifsp.cadernodigital.midia.application.gateway.PontuacaoGateway;
import br.edu.ifsp.cadernodigital.service.PontuacaoService;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
import br.edu.ifsp.cadernodigital.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PontuacaoGatewayImpl implements PontuacaoGateway {

    private final PontuacaoService pontuacaoService;
    private final UsuarioRepository usuarioRepository;

    public PontuacaoGatewayImpl(PontuacaoService pontuacaoService, UsuarioRepository usuarioRepository) {
        this.pontuacaoService = pontuacaoService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void pontuarEnvioDeMidia(Long autorId) {
        usuarioRepository.findById(autorId).ifPresent(pontuacaoService::pontuarEnvioDeMidia);
    }
}
