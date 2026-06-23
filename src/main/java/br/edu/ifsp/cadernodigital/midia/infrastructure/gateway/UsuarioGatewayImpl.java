package br.edu.ifsp.cadernodigital.midia.infrastructure.gateway;

import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.midia.application.gateway.UsuarioGateway;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
import br.edu.ifsp.cadernodigital.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    public UsuarioGatewayImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public String buscarNomeAutor(Long autorId) {
        return usuarioRepository.findById(autorId)
                .map(Usuario::getNome)
                .orElse("Autor Desconhecido");
    }

    @Override
    public void validarAutorExistente(Long autorId) {
        if (!usuarioRepository.existsById(autorId)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado.");
        }
    }
}
