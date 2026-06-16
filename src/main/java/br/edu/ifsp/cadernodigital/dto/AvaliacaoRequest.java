package br.edu.ifsp.cadernodigital.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequest(
        @NotNull Long usuarioId,
        @NotNull @Min(1) @Max(5) Integer nota,
        String comentario
) {
}
