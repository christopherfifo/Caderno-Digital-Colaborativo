package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.MidiaRequest;
import br.edu.ifsp.cadernodigital.dto.MidiaResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.Midia;
import br.edu.ifsp.cadernodigital.model.Usuario;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/midias")
public class MidiaController {

    private final MidiaRepository midiaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PontuacaoService pontuacaoService;

    public MidiaController(MidiaRepository midiaRepository, UsuarioRepository usuarioRepository,
                           PontuacaoService pontuacaoService) {
        this.midiaRepository = midiaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pontuacaoService = pontuacaoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MidiaResponse criar(@RequestBody @Valid MidiaRequest request) {
        Usuario autor = buscarUsuario(request.autorId());

        Midia midia = new Midia(
                request.titulo(),
                request.descricao(),
                request.urlArquivo(),
                request.tipo(),
                request.dataHoraAula(),
                request.disciplina(),
                request.professorResponsavel(),
                autor
        );

        Midia midiaSalva = midiaRepository.save(midia);
        pontuacaoService.pontuarEnvioDeMidia(autor);

        return MidiaResponse.fromEntity(midiaSalva);
    }

    @GetMapping
    public List<MidiaResponse> listar(@RequestParam(required = false) String disciplina) {
        List<Midia> midias;

        if (disciplina == null || disciplina.isBlank()) {
            midias = midiaRepository.findAll();
        } else {
            midias = midiaRepository.findByDisciplinaContainingIgnoreCase(disciplina);
        }

        return midias.stream()
                .map(MidiaResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public MidiaResponse buscarPorId(@PathVariable Long id) {
        return MidiaResponse.fromEntity(buscarMidia(id));
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
    }

    private Midia buscarMidia(Long id) {
        return midiaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
    }
}
