package br.edu.ifsp.cadernodigital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaterialComplementarRequest(
        @NotBlank String titulo,
        String descricao,
        @NotBlank String link,
        @NotNull Long autorId
) {
}
