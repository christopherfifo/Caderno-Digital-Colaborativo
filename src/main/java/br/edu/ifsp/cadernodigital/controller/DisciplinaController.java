package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.DisciplinaRequest;
import br.edu.ifsp.cadernodigital.dto.DisciplinaResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.Disciplina;
import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.model.enums.TipoUsuario;
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
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

  private final DisciplinaRepository disciplinaRepository;
  private final UsuarioRepository usuarioRepository;

  public DisciplinaController(
    DisciplinaRepository disciplinaRepository,
    UsuarioRepository usuarioRepository
  ) {
    this.disciplinaRepository = disciplinaRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DisciplinaResponse criar(
    @RequestBody @Valid DisciplinaRequest request
  ) {
    Disciplina disciplina = new Disciplina(
      request.nome(),
      request.codigo(),
      request.descricao()
    );

    if (
      request.professoresIds() != null && !request.professoresIds().isEmpty()
    ) {
      List<Usuario> professores = usuarioRepository.findAllById(
        request.professoresIds()
      );
      // Validar se todos são professores
      for (Usuario prof : professores) {
        if (prof.getTipo() != TipoUsuario.PROFESSOR) {
          throw new IllegalArgumentException(
            "O usuário com id " + prof.getId() + " não é um professor."
          );
        }
      }
      disciplina.setProfessores(professores);
    }

    Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
    return DisciplinaResponse.fromEntity(disciplinaSalva);
  }

  @GetMapping
  public List<DisciplinaResponse> listar() {
    return disciplinaRepository
      .findAll()
      .stream()
      .map(DisciplinaResponse::fromEntity)
      .toList();
  }

  @GetMapping("/{id}")
  public DisciplinaResponse buscarPorId(@PathVariable Long id) {
    Disciplina disciplina = buscarDisciplina(id);
    return DisciplinaResponse.fromEntity(disciplina);
  }

  @PutMapping("/{id}")
  public DisciplinaResponse atualizar(
    @PathVariable Long id,
    @RequestBody @Valid DisciplinaRequest request
  ) {
    Disciplina disciplina = buscarDisciplina(id);
    disciplina.setNome(request.nome());
    disciplina.setCodigo(request.codigo());
    disciplina.setDescricao(request.descricao());

    if (request.professoresIds() != null) {
      List<Usuario> professores = usuarioRepository.findAllById(
        request.professoresIds()
      );
      // Validar se todos são professores
      for (Usuario prof : professores) {
        if (prof.getTipo() != TipoUsuario.PROFESSOR) {
          throw new IllegalArgumentException(
            "O usuário com id " + prof.getId() + " não é um professor."
          );
        }
      }
      disciplina.setProfessores(professores);
    }

    Disciplina disciplinaAtualizada = disciplinaRepository.save(disciplina);
    return DisciplinaResponse.fromEntity(disciplinaAtualizada);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long id) {
    Disciplina disciplina = buscarDisciplina(id);
    disciplinaRepository.delete(disciplina);
  }

  private Disciplina buscarDisciplina(Long id) {
    return disciplinaRepository
      .findById(id)
      .orElseThrow(() ->
        new RecursoNaoEncontradoException("Disciplina não encontrada.")
      );
  }
}
