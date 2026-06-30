package br.edu.ifsp.gamificacaoservice.application.dto;

import br.edu.ifsp.gamificacaoservice.domain.model.TipoAcao;
import jakarta.validation.constraints.NotNull;

public record PontuarRequest(@NotNull Long usuarioId, @NotNull TipoAcao tipoAcao) {}
