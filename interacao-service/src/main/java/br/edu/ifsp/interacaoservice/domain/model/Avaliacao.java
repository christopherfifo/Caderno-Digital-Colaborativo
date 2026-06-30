package br.edu.ifsp.interacaoservice.domain.model;
import java.time.LocalDateTime;

public class Avaliacao {
    private Long id;
    private Integer nota;
    private String comentario;
    private TipoAvaliacao tipo;
    private Long usuarioId;
    private Long midiaId;
    private Long comentarioId;
    private LocalDateTime criadoEm;

    public Avaliacao() {}

    public Avaliacao(Integer nota, String comentario, TipoAvaliacao tipo,
                     Long usuarioId, Long midiaId, Long comentarioId) {
        this.nota = nota; this.comentario = comentario; this.tipo = tipo;
        this.usuarioId = usuarioId; this.midiaId = midiaId; this.comentarioId = comentarioId;
        this.criadoEm = LocalDateTime.now();
    }

    public Avaliacao(Long id, Integer nota, String comentario, TipoAvaliacao tipo,
                     Long usuarioId, Long midiaId, Long comentarioId, LocalDateTime criadoEm) {
        this.id = id; this.nota = nota; this.comentario = comentario; this.tipo = tipo;
        this.usuarioId = usuarioId; this.midiaId = midiaId; this.comentarioId = comentarioId;
        this.criadoEm = criadoEm;
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
