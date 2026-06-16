package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.AulaRequest;
import br.edu.ifsp.cadernodigital.dto.AulaResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.Aula;
import br.edu.ifsp.cadernodigital.model.Disciplina;
import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.model.enums.TipoUsuario;
import br.edu.ifsp.cadernodigital.repository.AulaRepository;
import br.edu.ifsp.cadernodigital.repository.DisciplinaRepository;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

  private final AulaRepository aulaRepository;
  private final DisciplinaRepository disciplinaRepository;
  private final UsuarioRepository usuarioRepository;

  public AulaController(
    AulaRepository aulaRepository,
    DisciplinaRepository disciplinaRepository,
    UsuarioRepository usuarioRepository
  ) {
    this.aulaRepository = aulaRepository;
    this.disciplinaRepository = disciplinaRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AulaResponse criar(@RequestBody @Valid AulaRequest request) {
    Disciplina disciplina = buscarDisciplina(request.disciplinaId());
    Usuario professor = buscarUsuario(request.professorId());

    if (professor.getTipo() != TipoUsuario.PROFESSOR) {
      throw new IllegalArgumentException(
        "O usuário responsável pela aula deve ser um professor."
      );
    }

    if (!disciplina.getProfessores().contains(professor)) {
      throw new IllegalArgumentException(
        "O professor não está associado a esta disciplina."
      );
    }

    Aula aula = new Aula(
      request.titulo(),
      request.descricao(),
      request.dataHora(),
      disciplina,
      professor
    );

    Aula aulaSalva = aulaRepository.save(aula);
    return AulaResponse.fromEntity(aulaSalva);
  }

  @GetMapping
  public List<AulaResponse> listar() {
    return aulaRepository
      .findAll()
      .stream()
      .map(AulaResponse::fromEntity)
      .toList();
  }

  @GetMapping("/disciplina/{disciplinaId}")
  public List<AulaResponse> listarPorDisciplina(
    @PathVariable Long disciplinaId
  ) {
    return aulaRepository
      .findByDisciplinaId(disciplinaId)
      .stream()
      .map(AulaResponse::fromEntity)
      .toList();
  }

  @GetMapping("/{id}")
  public AulaResponse buscarPorId(@PathVariable Long id) {
    Aula aula = buscarAula(id);
    return AulaResponse.fromEntity(aula);
  }

  @PutMapping("/{id}")
  public AulaResponse atualizar(
    @PathVariable Long id,
    @RequestBody @Valid AulaRequest request
  ) {
    Aula aula = buscarAula(id);
    Disciplina disciplina = buscarDisciplina(request.disciplinaId());
    Usuario professor = buscarUsuario(request.professorId());

    if (professor.getTipo() != TipoUsuario.PROFESSOR) {
      throw new IllegalArgumentException(
        "O usuário responsável pela aula deve ser um professor."
      );
    }

    if (!disciplina.getProfessores().contains(professor)) {
      throw new IllegalArgumentException(
        "O professor não está associado a esta disciplina."
      );
    }

    aula.setTitulo(request.titulo());
    aula.setDescricao(request.descricao());
    aula.setDataHora(request.dataHora());
    aula.setDisciplina(disciplina);
    aula.setProfessor(professor);

    Aula aulaAtualizada = aulaRepository.save(aula);
    return AulaResponse.fromEntity(aulaAtualizada);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long id) {
    Aula aula = buscarAula(id);
    aulaRepository.delete(aula);
  }

  private Aula buscarAula(Long id) {
    return aulaRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Aula não encontrada.")
      );
  }

  private Disciplina buscarDisciplina(Long id) {
    return disciplinaRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Disciplina não encontrada.")
      );
  }

  private Usuario buscarUsuario(Long id) {
    return usuarioRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Usuário não encontrado.")
      );
  }
}
