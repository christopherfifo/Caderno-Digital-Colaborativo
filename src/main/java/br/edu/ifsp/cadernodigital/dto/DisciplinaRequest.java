package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Disciplina;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record DisciplinaRequest(
  @NotBlank String nome,
  @NotBlank String codigo,
  String descricao,
  List<Long> professoresIds
) {}
