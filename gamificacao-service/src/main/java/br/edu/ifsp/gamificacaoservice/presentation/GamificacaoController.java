package br.edu.ifsp.gamificacaoservice.presentation;
import br.edu.ifsp.gamificacaoservice.application.dto.*;
import br.edu.ifsp.gamificacaoservice.application.service.GamificacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gamificacao")
public class GamificacaoController {
    private final GamificacaoService gamificacaoService;

    public GamificacaoController(GamificacaoService gamificacaoService) {
        this.gamificacaoService = gamificacaoService;
    }

    @PostMapping("/pontuar")
    @ResponseStatus(HttpStatus.CREATED)
    public PontuacaoResponse pontuar(@RequestBody @Valid PontuarRequest request) {
        return gamificacaoService.pontuar(request);
    }

    @GetMapping("/pontuacao/{usuarioId}")
    public PontuacaoResponse buscarPontuacao(@PathVariable Long usuarioId) {
        return gamificacaoService.buscarPontuacao(usuarioId);
    }

    @GetMapping("/ranking")
    public List<RankingItemResponse> listarRanking() {
        return gamificacaoService.listarRanking();
    }
}
