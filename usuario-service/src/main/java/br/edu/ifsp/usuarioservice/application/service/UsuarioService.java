package br.edu.ifsp.usuarioservice.application.service;

import br.edu.ifsp.usuarioservice.application.dto.*;
import br.edu.ifsp.usuarioservice.domain.model.Usuario;
import br.edu.ifsp.usuarioservice.domain.repository.UsuarioDomainRepository;
import br.edu.ifsp.usuarioservice.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioDomainRepository usuarioRepository;

    public UsuarioService(UsuarioDomainRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse criar(UsuarioRequest request) {
        if (usuarioRepository.buscarPorEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }
        Usuario usuario = new Usuario(request.nome(), request.email(), request.tipo());
        return UsuarioResponse.fromDomain(usuarioRepository.salvar(usuario));
    }

    public List<UsuarioResponse> listar() {
        return usuarioRepository.listarTodos().stream().map(UsuarioResponse::fromDomain).toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        return UsuarioResponse.fromDomain(
            usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."))
        );
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.buscarPorId(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setTipo(request.tipo());
        return UsuarioResponse.fromDomain(usuarioRepository.salvar(usuario));
    }

    public void excluir(Long id) {
        if (!usuarioRepository.existePorId(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado.");
        }
        usuarioRepository.excluir(id);
    }

    public InternalUsuarioResponse buscarInternoPorId(Long id) {
        return InternalUsuarioResponse.fromDomain(
            usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."))
        );
    }

    public boolean existePorId(Long id) {
        return usuarioRepository.existePorId(id);
    }
}
