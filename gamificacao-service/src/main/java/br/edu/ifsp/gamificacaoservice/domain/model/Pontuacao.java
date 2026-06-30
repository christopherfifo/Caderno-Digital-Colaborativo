package br.edu.ifsp.gamificacaoservice.domain.model;

public class Pontuacao {
    private Long id;
    private Long usuarioId;
    private Integer pontos;

    public Pontuacao() {}

    public Pontuacao(Long usuarioId) {
        this.usuarioId = usuarioId;
        this.pontos = 0;
    }

    public Pontuacao(Long id, Long usuarioId, Integer pontos) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.pontos = pontos;
    }

    public void adicionarPontos(int quantidade) { this.pontos += quantidade; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Integer getPontos() { return pontos; }
    public void setPontos(Integer pontos) { this.pontos = pontos; }
}
