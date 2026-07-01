package br.edu.ifsp.interacaoservice.infrastructure.persistence;
import br.edu.ifsp.interacaoservice.domain.model.Avaliacao;
import br.edu.ifsp.interacaoservice.domain.model.TipoAvaliacao;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes")
public class AvaliacaoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nota;
    private String comentario;
    @Enumerated(EnumType.STRING)
    private TipoAvaliacao tipo;
    @Column(name = "usuario_id")
    private Long usuarioId;
    @Column(name = "midia_id")
    private Long midiaId;
    @Column(name = "comentario_id")
    private Long comentarioId;
    private LocalDateTime criadoEm;

    public AvaliacaoEntity() {}

    public Avaliacao toDomain() {
        return new Avaliacao(id, nota, comentario, tipo, usuarioId, midiaId, comentarioId, criadoEm);
    }

    public static AvaliacaoEntity fromDomain(Avaliacao a) {
        AvaliacaoEntity e = new AvaliacaoEntity();
        e.id = a.getId(); e.nota = a.getNota(); e.comentario = a.getComentario();
        e.tipo = a.getTipo(); e.usuarioId = a.getUsuarioId(); e.midiaId = a.getMidiaId();
        e.comentarioId = a.getComentarioId(); e.criadoEm = a.getCriadoEm();
        return e;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public TipoAvaliacao getTipo() { return tipo; }
    public void setTipo(TipoAvaliacao tipo) { this.tipo = tipo; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getMidiaId() { return midiaId; }
    public void setMidiaId(Long midiaId) { this.midiaId = midiaId; }
    public Long getComentarioId() { return comentarioId; }
    public void setComentarioId(Long comentarioId) { this.comentarioId = comentarioId; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
