package br.edu.ifsp.interacaoservice.presentation;
import br.edu.ifsp.interacaoservice.application.dto.*;
import br.edu.ifsp.interacaoservice.application.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AvaliacaoController {
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/midias/{midiaId}/avaliacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoResponse avaliarMidia(@PathVariable Long midiaId,
                                          @RequestBody @Valid AvaliacaoRequest request) {
        return avaliacaoService.avaliarMidia(midiaId, request);
    }

    @GetMapping("/midias/{midiaId}/avaliacoes")
    public List<AvaliacaoResponse> listarPorMidia(@PathVariable Long midiaId) {
        return avaliacaoService.listarPorMidia(midiaId);
    }

    @PostMapping("/comentarios/{comentarioId}/avaliacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public AvaliacaoResponse avaliarComentario(@PathVariable Long comentarioId,
                                               @RequestBody @Valid AvaliacaoRequest request) {
        return avaliacaoService.avaliarComentario(comentarioId, request);
    }

    @GetMapping("/comentarios/{comentarioId}/avaliacoes")
    public List<AvaliacaoResponse> listarPorComentario(@PathVariable Long comentarioId) {
        return avaliacaoService.listarPorComentario(comentarioId);
    }
}
