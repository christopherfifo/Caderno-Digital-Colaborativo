package br.edu.ifsp.midiaservice.presentation;
import br.edu.ifsp.midiaservice.application.dto.*;
import br.edu.ifsp.midiaservice.application.service.MidiaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/midias")
public class MidiaController {
    private final MidiaService midiaService;

    public MidiaController(MidiaService midiaService) {
        this.midiaService = midiaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MidiaResponse criar(@RequestBody @Valid MidiaRequest request) {
        return midiaService.criar(request);
    }

    @GetMapping
    public List<MidiaResponse> listar(
            @RequestParam(required = false) String disciplina,
            @RequestParam(required = false) String professor,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return midiaService.listar(disciplina, professor, data);
    }

    @GetMapping("/{id}")
    public MidiaResponse buscarPorId(@PathVariable Long id) {
        return midiaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public MidiaResponse atualizar(@PathVariable Long id, @RequestBody @Valid MidiaRequest request) {
        return midiaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        midiaService.excluir(id);
    }
}
