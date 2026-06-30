package br.edu.ifsp.gamificacaoservice.infrastructure.persistence;

import br.edu.ifsp.gamificacaoservice.domain.model.Pontuacao;
import jakarta.persistence.*;

@Entity
@Table(name = "pontuacoes")
public class PontuacaoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long usuarioId;

    private Integer pontos;

    public PontuacaoEntity() {}

    public PontuacaoEntity(Long id, Long usuarioId, Integer pontos) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.pontos = pontos;
    }

    public Pontuacao toDomain() {
        return new Pontuacao(id, usuarioId, pontos);
    }

    public static PontuacaoEntity fromDomain(Pontuacao p) {
        return new PontuacaoEntity(p.getId(), p.getUsuarioId(), p.getPontos());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Integer getPontos() { return pontos; }
    public void setPontos(Integer pontos) { this.pontos = pontos; }
}
