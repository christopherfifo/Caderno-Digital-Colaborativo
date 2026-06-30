package br.edu.ifsp.interacaoservice.application.service;
import br.edu.ifsp.interacaoservice.application.dto.*;
import br.edu.ifsp.interacaoservice.application.gateway.GamificacaoGateway;
import br.edu.ifsp.interacaoservice.application.gateway.UsuarioGateway;
import br.edu.ifsp.interacaoservice.domain.model.Avaliacao;
import br.edu.ifsp.interacaoservice.domain.model.Comentario;
import br.edu.ifsp.interacaoservice.domain.model.TipoAvaliacao;
import br.edu.ifsp.interacaoservice.domain.repository.AvaliacaoDomainRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvaliacaoService {
    private final AvaliacaoDomainRepository avaliacaoRepository;
    private final ComentarioService comentarioService;
    private final UsuarioGateway usuarioGateway;
    private final GamificacaoGateway gamificacaoGateway;

    public AvaliacaoService(AvaliacaoDomainRepository avaliacaoRepository,
                            ComentarioService comentarioService,
                            UsuarioGateway usuarioGateway,
                            GamificacaoGateway gamificacaoGateway) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.comentarioService = comentarioService;
        this.usuarioGateway = usuarioGateway;
        this.gamificacaoGateway = gamificacaoGateway;
    }

    public AvaliacaoResponse avaliarMidia(Long midiaId, AvaliacaoRequest request) {
        usuarioGateway.validarUsuarioExistente(request.usuarioId());
        Avaliacao avaliacao = new Avaliacao(request.nota(), request.comentario(),
            TipoAvaliacao.MIDIA, request.usuarioId(), midiaId, null);
        Avaliacao salva = avaliacaoRepository.salvar(avaliacao);
        try {
            gamificacaoGateway.pontuar(request.usuarioId(), "AVALIACAO");
            if (request.nota() >= 4) {
                Long autorMidiaId = usuarioGateway.buscarAutorDaMidia(midiaId);
                if (autorMidiaId != null) {
                    gamificacaoGateway.pontuar(autorMidiaId, "CONTRIBUICAO_BEM_AVALIADA");
                }
            }
        } catch (Exception ignored) {}
        return AvaliacaoResponse.fromDomain(salva);
    }

    public List<AvaliacaoResponse> listarPorMidia(Long midiaId) {
        return avaliacaoRepository.listarPorMidiaId(midiaId).stream()
            .map(AvaliacaoResponse::fromDomain).toList();
    }

    public AvaliacaoResponse avaliarComentario(Long comentarioId, AvaliacaoRequest request) {
        Comentario comentario = comentarioService.buscarPorId(comentarioId);
        usuarioGateway.validarUsuarioExistente(request.usuarioId());
        Avaliacao avaliacao = new Avaliacao(request.nota(), request.comentario(),
            TipoAvaliacao.COMENTARIO, request.usuarioId(), null, comentarioId);
        Avaliacao salva = avaliacaoRepository.salvar(avaliacao);
        try {
            gamificacaoGateway.pontuar(request.usuarioId(), "AVALIACAO");
            if (request.nota() >= 4) {
                gamificacaoGateway.pontuar(comentario.getAutorId(), "CONTRIBUICAO_BEM_AVALIADA");
            }
        } catch (Exception ignored) {}
        return AvaliacaoResponse.fromDomain(salva);
    }

    public List<AvaliacaoResponse> listarPorComentario(Long comentarioId) {
        return avaliacaoRepository.listarPorComentarioId(comentarioId).stream()
            .map(AvaliacaoResponse::fromDomain).toList();
    }
}
