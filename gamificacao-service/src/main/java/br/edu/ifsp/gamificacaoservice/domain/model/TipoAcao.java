package br.edu.ifsp.gamificacaoservice.domain.model;

public enum TipoAcao {
    ENVIO_MIDIA(10),
    COMENTARIO(5),
    AVALIACAO(2),
    CONTRIBUICAO_BEM_AVALIADA(3);

    private final int pontos;

    TipoAcao(int pontos) { this.pontos = pontos; }

    public int getPontos() { return pontos; }
}
