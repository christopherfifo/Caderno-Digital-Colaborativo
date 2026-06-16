package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Disciplina;
import br.edu.ifsp.cadernodigital.model.Usuario;
import java.util.List;

public record DisciplinaResponse(
  Long id,
  String nome,
  String codigo,
  String descricao,
  List<UsuarioResumo> professores
) {
  public static DisciplinaResponse fromEntity(Disciplina disciplina) {
    return new DisciplinaResponse(
      disciplina.getId(),
      disciplina.getNome(),
      disciplina.getCodigo(),
      disciplina.getDescricao(),
      disciplina
        .getProfessores()
        .stream()
        .map(p -> new UsuarioResumo(p.getId(), p.getNome()))
        .toList()
    );
  }

  public record UsuarioResumo(Long id, String nome) {}
}
