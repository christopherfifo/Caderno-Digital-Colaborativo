package br.edu.ifsp.interacaoservice.presentation;
import br.edu.ifsp.interacaoservice.application.dto.*;
import br.edu.ifsp.interacaoservice.application.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping("/midias/{midiaId}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioResponse comentar(@PathVariable Long midiaId,
                                       @RequestBody @Valid ComentarioRequest request) {
        return comentarioService.comentar(midiaId, request);
    }

    @GetMapping("/midias/{midiaId}/comentarios")
    public List<ComentarioResponse> listarPorMidia(@PathVariable Long midiaId) {
        return comentarioService.listarPorMidia(midiaId);
    }

    @PostMapping("/comentarios/{comentarioId}/respostas")
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioResponse responder(@PathVariable Long comentarioId,
                                        @RequestBody @Valid ComentarioRequest request) {
        return comentarioService.responder(comentarioId, request);
    }

    @GetMapping("/comentarios/{comentarioId}/respostas")
    public List<ComentarioResponse> listarRespostas(@PathVariable Long comentarioId) {
        return comentarioService.listarRespostas(comentarioId);
    }
}
