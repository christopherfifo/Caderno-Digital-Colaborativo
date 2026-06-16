package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.MaterialComplementar;

import java.time.LocalDateTime;

public record MaterialComplementarResponse(
        Long id,
        String titulo,
        String descricao,
        String link,
        LocalDateTime criadoEm,
        Long midiaId,
        Long autorId,
        String nomeAutor
) {
    public static MaterialComplementarResponse fromEntity(MaterialComplementar material) {
        return new MaterialComplementarResponse(
                material.getId(),
                material.getTitulo(),
                material.getDescricao(),
                material.getLink(),
                material.getCriadoEm(),
                material.getMidia().getId(),
                material.getAutor().getId(),
                material.getAutor().getNome()
        );
    }
}
