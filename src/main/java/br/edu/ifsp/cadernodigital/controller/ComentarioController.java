package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.ComentarioRequest;
import br.edu.ifsp.cadernodigital.dto.ComentarioResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.Comentario;
import br.edu.ifsp.cadernodigital.model.Midia;
import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.repository.ComentarioRepository;
import br.edu.ifsp.cadernodigital.repository.MidiaRepository;
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
public class ComentarioController {

    private final ComentarioRepository comentarioRepository;
    private final MidiaRepository midiaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PontuacaoService pontuacaoService;

    public ComentarioController(ComentarioRepository comentarioRepository, MidiaRepository midiaRepository,
                                UsuarioRepository usuarioRepository, PontuacaoService pontuacaoService) {
        this.comentarioRepository = comentarioRepository;
        this.midiaRepository = midiaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pontuacaoService = pontuacaoService;
    }

    @PostMapping("/midias/{midiaId}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioResponse comentar(@PathVariable Long midiaId, @RequestBody @Valid ComentarioRequest request) {
        Midia midia = buscarMidia(midiaId);
        Usuario autor = buscarUsuario(request.autorId());

        Comentario comentario = new Comentario(request.texto(), request.linkComplementar(), midia, autor, null);
        Comentario comentarioSalvo = comentarioRepository.save(comentario);
        pontuacaoService.pontuarComentario(autor);

        return ComentarioResponse.fromEntity(comentarioSalvo);
    }

    @GetMapping("/midias/{midiaId}/comentarios")
    public List<ComentarioResponse> listarComentariosDaMidia(@PathVariable Long midiaId) {
        Midia midia = buscarMidia(midiaId);

        return comentarioRepository.findByMidiaAndComentarioPaiIsNullOrderByCriadoEmAsc(midia)
                .stream()
                .map(ComentarioResponse::fromEntity)
                .toList();
    }

    @PostMapping("/comentarios/{comentarioId}/respostas")
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioResponse responder(@PathVariable Long comentarioId, @RequestBody @Valid ComentarioRequest request) {
        Comentario comentarioPai = buscarComentario(comentarioId);
        Usuario autor = buscarUsuario(request.autorId());

        Comentario resposta = new Comentario(
                request.texto(),
                request.linkComplementar(),
                comentarioPai.getMidia(),
                autor,
                comentarioPai
        );

        Comentario respostaSalva = comentarioRepository.save(resposta);
        pontuacaoService.pontuarComentario(autor);

        return ComentarioResponse.fromEntity(respostaSalva);
    }

    @GetMapping("/comentarios/{comentarioId}/respostas")
    public List<ComentarioResponse> listarRespostas(@PathVariable Long comentarioId) {
        Comentario comentarioPai = buscarComentario(comentarioId);

        return comentarioRepository.findByComentarioPaiOrderByCriadoEmAsc(comentarioPai)
                .stream()
                .map(ComentarioResponse::fromEntity)
                .toList();
    }

    private Midia buscarMidia(Long id) {
        return midiaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
    }

    private Comentario buscarComentario(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Comentário não encontrado."));
    }
}
