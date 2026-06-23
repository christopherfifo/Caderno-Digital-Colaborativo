package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.AvaliacaoRequest;
import br.edu.ifsp.cadernodigital.dto.AvaliacaoResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.Avaliacao;
import br.edu.ifsp.cadernodigital.model.Comentario;
import br.edu.ifsp.cadernodigital.midia.infrastructure.persistence.MidiaEntity;
import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.model.enums.TipoAvaliacao;
import br.edu.ifsp.cadernodigital.repository.AvaliacaoRepository;
import br.edu.ifsp.cadernodigital.repository.ComentarioRepository;
import br.edu.ifsp.cadernodigital.midia.infrastructure.persistence.MidiaJpaRepository;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
import br.edu.ifsp.cadernodigital.service.PontuacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacaoRepository;
    private final MidiaJpaRepository midiaRepository;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PontuacaoService pontuacaoService;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository, MidiaJpaRepository midiaRepository,
                               ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository,
                               PontuacaoService pontuacaoService) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.midiaRepository = midiaRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.pontuacaoService = pontuacaoService;
    }

    @PostMapping("/midias/{midiaId}/avaliacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoResponse avaliarMidia(@PathVariable Long midiaId, @RequestBody @Valid AvaliacaoRequest request) {
        MidiaEntity midia = buscarMidia(midiaId);
        Usuario usuario = buscarUsuario(request.usuarioId());

        Avaliacao avaliacao = new Avaliacao(
                request.nota(),
                request.comentario(),
                TipoAvaliacao.MIDIA,
                usuario,
                midia,
                null
        );

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        pontuarAvaliacao(usuario, midia.getAutor(), request.nota());

        return AvaliacaoResponse.fromEntity(avaliacaoSalva);
    }

    @GetMapping("/midias/{midiaId}/avaliacoes")
    public List<AvaliacaoResponse> listarAvaliacoesDaMidia(@PathVariable Long midiaId) {
        MidiaEntity midia = buscarMidia(midiaId);

        return avaliacaoRepository.findByMidia(midia)
                .stream()
                .map(AvaliacaoResponse::fromEntity)
                .toList();
    }

    @PostMapping("/comentarios/{comentarioId}/avaliacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoResponse avaliarComentario(@PathVariable Long comentarioId,
                                               @RequestBody @Valid AvaliacaoRequest request) {
        Comentario comentarioAvaliado = buscarComentario(comentarioId);
        Usuario usuario = buscarUsuario(request.usuarioId());

        Avaliacao avaliacao = new Avaliacao(
                request.nota(),
                request.comentario(),
                TipoAvaliacao.COMENTARIO,
                usuario,
                null,
                comentarioAvaliado
        );

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        pontuarAvaliacao(usuario, comentarioAvaliado.getAutor(), request.nota());

        return AvaliacaoResponse.fromEntity(avaliacaoSalva);
    }

    @GetMapping("/comentarios/{comentarioId}/avaliacoes")
    public List<AvaliacaoResponse> listarAvaliacoesDoComentario(@PathVariable Long comentarioId) {
        Comentario comentario = buscarComentario(comentarioId);

        return avaliacaoRepository.findByComentarioAvaliado(comentario)
                .stream()
                .map(AvaliacaoResponse::fromEntity)
                .toList();
    }

    private void pontuarAvaliacao(Usuario avaliador, Usuario autorAvaliado, Integer nota) {
        pontuacaoService.pontuarAvaliacao(avaliador);

        if (nota >= 4) {
            pontuacaoService.pontuarContribuicaoBemAvaliada(autorAvaliado);
        }
    }

    private MidiaEntity buscarMidia(Long id) {
        return midiaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
    }

    private Comentario buscarComentario(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Comentário não encontrado."));
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
    }
}
