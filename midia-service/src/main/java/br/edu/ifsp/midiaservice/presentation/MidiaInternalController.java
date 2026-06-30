package br.edu.ifsp.midiaservice.presentation;
import br.edu.ifsp.midiaservice.application.dto.InternalMidiaResponse;
import br.edu.ifsp.midiaservice.application.service.MidiaService;
import br.edu.ifsp.midiaservice.exception.RecursoNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/midias")
public class MidiaInternalController {
    private final MidiaService midiaService;

    public MidiaInternalController(MidiaService midiaService) {
        this.midiaService = midiaService;
    }

    @GetMapping("/{id}")
    public InternalMidiaResponse buscarPorId(@PathVariable Long id) {
        return midiaService.buscarInternoPorId(id);
    }

    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        try {
            midiaService.buscarInternoPorId(id);
            return ResponseEntity.ok(true);
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
