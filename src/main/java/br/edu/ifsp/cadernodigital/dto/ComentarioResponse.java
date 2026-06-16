package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Comentario;
import java.time.LocalDateTime;

public record ComentarioResponse(
  Long id,
  String texto,
  String linkComplementar,
  LocalDateTime criadoEm,
  Long midiaId,
  Long autorId,
  String nomeAutor,
  Long comentarioPaiId
) {
  public static ComentarioResponse fromEntity(Comentario comentario) {
    Long comentarioPaiId = null;
    if (comentario.getComentarioPai() != null) {
      comentarioPaiId = comentario.getComentarioPai().getId();
    }

    return new ComentarioResponse(
      comentario.getId(),
      comentario.getTexto(),
      comentario.getLinkComplementar(),
      comentario.getCriadoEm(),
      comentario.getMidia().getId(),
      comentario.getAutor().getId(),
      comentario.getAutor().getNome(),
      comentarioPaiId
    );
  }
}
