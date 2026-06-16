package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Usuario;

public record RankingResponse(
  int posicao,
  Long usuarioId,
  String nome,
  Integer pontos
) {
  public static RankingResponse fromEntity(int posicao, Usuario usuario) {
    return new RankingResponse(
      posicao,
      usuario.getId(),
      usuario.getNome(),
      usuario.getPontos()
    );
  }
}
