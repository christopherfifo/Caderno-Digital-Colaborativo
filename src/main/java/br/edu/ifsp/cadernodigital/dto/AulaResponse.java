package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Aula;
import java.time.LocalDateTime;

public record AulaResponse(
  Long id,
  String titulo,
  String descricao,
  LocalDateTime dataHora,
  Long disciplinaId,
  String disciplinaNome,
  Long professorId,
  String professorNome
) {
  public static AulaResponse fromEntity(Aula aula) {
    return new AulaResponse(
      aula.getId(),
      aula.getTitulo(),
      aula.getDescricao(),
      aula.getDataHora(),
      aula.getDisciplina().getId(),
      aula.getDisciplina().getNome(),
      aula.getProfessor().getId(),
      aula.getProfessor().getNome()
    );
  }
}
