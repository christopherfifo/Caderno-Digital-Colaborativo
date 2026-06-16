package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Avaliacao;
import br.edu.ifsp.cadernodigital.model.enums.TipoAvaliacao;

import java.time.LocalDateTime;

public record AvaliacaoResponse(
        Long id,
        Integer nota,
        String comentario,
        TipoAvaliacao tipo,
        LocalDateTime criadoEm,
        Long usuarioId,
        Long midiaId,
        Long comentarioAvaliadoId
) {
    public static AvaliacaoResponse fromEntity(Avaliacao avaliacao) {
        Long midiaId = null;
        if (avaliacao.getMidia() != null) {
            midiaId = avaliacao.getMidia().getId();
        }

        Long comentarioAvaliadoId = null;
        if (avaliacao.getComentarioAvaliado() != null) {
            comentarioAvaliadoId = avaliacao.getComentarioAvaliado().getId();
        }

        return new AvaliacaoResponse(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getTipo(),
                avaliacao.getCriadoEm(),
                avaliacao.getUsuario().getId(),
                midiaId,
                comentarioAvaliadoId
        );
    }
}
