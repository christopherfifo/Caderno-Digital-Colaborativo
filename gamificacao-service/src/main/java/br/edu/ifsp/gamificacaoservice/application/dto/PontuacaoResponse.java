package br.edu.ifsp.gamificacaoservice.application.dto;

import br.edu.ifsp.gamificacaoservice.domain.model.Pontuacao;

public record PontuacaoResponse(Long usuarioId, Integer pontos) {
    public static PontuacaoResponse fromDomain(Pontuacao p) {
        return new PontuacaoResponse(p.getUsuarioId(), p.getPontos());
    }
}
