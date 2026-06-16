package br.edu.ifsp.cadernodigital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AulaRequest(
  @NotBlank String titulo,
  String descricao,
  @NotNull LocalDateTime dataHora,
  @NotNull Long disciplinaId,
  @NotNull Long professorId
) {}
