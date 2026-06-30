package br.edu.ifsp.interacaoservice.application.service;
import br.edu.ifsp.interacaoservice.application.dto.*;
import br.edu.ifsp.interacaoservice.application.gateway.GamificacaoGateway;
import br.edu.ifsp.interacaoservice.application.gateway.UsuarioGateway;
import br.edu.ifsp.interacaoservice.domain.model.Comentario;
import br.edu.ifsp.interacaoservice.domain.repository.ComentarioDomainRepository;
import br.edu.ifsp.interacaoservice.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComentarioService {
    private final ComentarioDomainRepository comentarioRepository;
    private final UsuarioGateway usuarioGateway;
    private final GamificacaoGateway gamificacaoGateway;

    public ComentarioService(ComentarioDomainRepository comentarioRepository,
                             UsuarioGateway usuarioGateway,
                             GamificacaoGateway gamificacaoGateway) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioGateway = usuarioGateway;
        this.gamificacaoGateway = gamificacaoGateway;
    }

    public ComentarioResponse comentar(Long midiaId, ComentarioRequest request) {
        usuarioGateway.validarUsuarioExistente(request.autorId());
        String nomeAutor = usuarioGateway.buscarNomeUsuario(request.autorId());
        Comentario comentario = new Comentario(request.texto(), request.linkComplementar(),
            midiaId, request.autorId(), null);
        comentario.setNomeAutor(nomeAutor);
        Comentario salvo = comentarioRepository.salvar(comentario);
        try { gamificacaoGateway.pontuar(request.autorId(), "COMENTARIO"); } catch (Exception ignored) {}
        return ComentarioResponse.fromDomain(salvo);
    }

    public List<ComentarioResponse> listarPorMidia(Long midiaId) {
        return comentarioRepository.listarPorMidiaIdSemPai(midiaId).stream()
            .map(ComentarioResponse::fromDomain).toList();
    }

    public ComentarioResponse responder(Long comentarioPaiId, ComentarioRequest request) {
        Comentario pai = comentarioRepository.buscarPorId(comentarioPaiId)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Comentário não encontrado."));
        usuarioGateway.validarUsuarioExistente(request.autorId());
        String nomeAutor = usuarioGateway.buscarNomeUsuario(request.autorId());
        Comentario resposta = new Comentario(request.texto(), request.linkComplementar(),
            pai.getMidiaId(), request.autorId(), comentarioPaiId);
        resposta.setNomeAutor(nomeAutor);
        Comentario salva = comentarioRepository.salvar(resposta);
        try { gamificacaoGateway.pontuar(request.autorId(), "COMENTARIO"); } catch (Exception ignored) {}
        return ComentarioResponse.fromDomain(salva);
    }

    public List<ComentarioResponse> listarRespostas(Long comentarioPaiId) {
        return comentarioRepository.listarRespostas(comentarioPaiId).stream()
            .map(ComentarioResponse::fromDomain).toList();
    }

    public Comentario buscarPorId(Long id) {
        return comentarioRepository.buscarPorId(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Comentário não encontrado."));
    }
}
