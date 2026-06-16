package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.MidiaRequest;
import br.edu.ifsp.cadernodigital.dto.MidiaResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.Aula;
import br.edu.ifsp.cadernodigital.model.Disciplina;
import br.edu.ifsp.cadernodigital.model.Midia;
import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.repository.AulaRepository;
import br.edu.ifsp.cadernodigital.repository.DisciplinaRepository;
import br.edu.ifsp.cadernodigital.repository.MidiaRepository;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
import br.edu.ifsp.cadernodigital.service.PontuacaoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/midias")
public class MidiaController {

  private final MidiaRepository midiaRepository;
  private final UsuarioRepository usuarioRepository;
  private final DisciplinaRepository disciplinaRepository;
  private final AulaRepository aulaRepository;
  private final PontuacaoService pontuacaoService;

  public MidiaController(
    MidiaRepository midiaRepository,
    UsuarioRepository usuarioRepository,
    DisciplinaRepository disciplinaRepository,
    AulaRepository aulaRepository,
    PontuacaoService pontuacaoService
  ) {
    this.midiaRepository = midiaRepository;
    this.usuarioRepository = usuarioRepository;
    this.disciplinaRepository = disciplinaRepository;
    this.aulaRepository = aulaRepository;
    this.pontuacaoService = pontuacaoService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MidiaResponse criar(@RequestBody @Valid MidiaRequest request) {
    Usuario autor = buscarUsuario(request.autorId());
    Disciplina disciplina = buscarDisciplina(request.disciplinaId());
    Aula aula = buscarAula(request.aulaId());

    Midia midia = new Midia(
      request.titulo(),
      request.descricao(),
      request.urlArquivo(),
      request.tipo(),
      request.dataHoraAula(),
      disciplina,
      aula,
      autor
    );

    Midia midiaSalva = midiaRepository.save(midia);
    pontuacaoService.pontuarEnvioDeMidia(autor);

    return MidiaResponse.fromEntity(midiaSalva);
  }

  @GetMapping
  public List<MidiaResponse> listar(
    @RequestParam(required = false) String disciplina,
    @RequestParam(required = false) String professor
  ) {
    List<Midia> midias;

    boolean temDisciplina = disciplina != null && !disciplina.isBlank();
    boolean temProfessor = professor != null && !professor.isBlank();

    if (temDisciplina && temProfessor) {
      midias =
        midiaRepository.findByDisciplinaNomeContainingIgnoreCaseAndAulaProfessorNomeContainingIgnoreCase(
          disciplina,
          professor
        );
    } else if (temDisciplina) {
      midias = midiaRepository.findByDisciplinaNomeContainingIgnoreCase(
        disciplina
      );
    } else if (temProfessor) {
      midias = midiaRepository.findByAulaProfessorNomeContainingIgnoreCase(
        professor
      );
    } else {
      midias = midiaRepository.findAll();
    }

    return midias.stream().map(MidiaResponse::fromEntity).toList();
  }

  @GetMapping("/{id}")
  public MidiaResponse buscarPorId(@PathVariable Long id) {
    return MidiaResponse.fromEntity(buscarMidia(id));
  }

  @PutMapping("/{id}")
  public MidiaResponse atualizar(
    @PathVariable Long id,
    @RequestBody @Valid MidiaRequest request
  ) {
    Midia midia = buscarMidia(id);
    Disciplina disciplina = buscarDisciplina(request.disciplinaId());
    Aula aula = buscarAula(request.aulaId());

    midia.setTitulo(request.titulo());
    midia.setDescricao(request.descricao());
    midia.setUrlArquivo(request.urlArquivo());
    midia.setTipo(request.tipo());
    midia.setDataHoraAula(request.dataHoraAula());
    midia.setDisciplina(disciplina);
    midia.setAula(aula);

    Midia midiaAtualizada = midiaRepository.save(midia);
    return MidiaResponse.fromEntity(midiaAtualizada);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long id) {
    Midia midia = buscarMidia(id);
    midiaRepository.delete(midia);
  }

  private Usuario buscarUsuario(Long id) {
    return usuarioRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Usuário não encontrado.")
      );
  }

  private Disciplina buscarDisciplina(Long id) {
    return disciplinaRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Disciplina não encontrada.")
      );
  }

  private Aula buscarAula(Long id) {
    return aulaRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Aula não encontrada.")
      );
  }

  private Midia buscarMidia(Long id) {
    return midiaRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Mídia não encontrada.")
      );
  }
}
