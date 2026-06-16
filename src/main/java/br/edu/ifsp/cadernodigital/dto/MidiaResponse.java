package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Midia;
import br.edu.ifsp.cadernodigital.model.enums.TipoMidia;
import java.time.LocalDateTime;

public record MidiaResponse(
  Long id,
  String titulo,
  String descricao,
  String urlArquivo,
  TipoMidia tipo,
  LocalDateTime dataHoraAula,
  Long disciplinaId,
  String disciplinaNome,
  Long aulaId,
  String aulaTitulo,
  String professorResponsavel,
  LocalDateTime criadoEm,
  Long autorId,
  String nomeAutor
) {
  public static MidiaResponse fromEntity(Midia midia) {
    return new MidiaResponse(
      midia.getId(),
      midia.getTitulo(),
      midia.getDescricao(),
      midia.getUrlArquivo(),
      midia.getTipo(),
      midia.getDataHoraAula(),
      midia.getDisciplina() != null ? midia.getDisciplina().getId() : null,
      midia.getDisciplina() != null ? midia.getDisciplina().getNome() : null,
      midia.getAula() != null ? midia.getAula().getId() : null,
      midia.getAula() != null ? midia.getAula().getTitulo() : null,
      midia.getAula() != null && midia.getAula().getProfessor() != null
        ? midia.getAula().getProfessor().getNome()
        : null,
      midia.getCriadoEm(),
      midia.getAutor().getId(),
      midia.getAutor().getNome()
    );
  }
}
