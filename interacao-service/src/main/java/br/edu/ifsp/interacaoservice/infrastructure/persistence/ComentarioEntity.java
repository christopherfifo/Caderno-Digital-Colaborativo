package br.edu.ifsp.interacaoservice.infrastructure.persistence;
import br.edu.ifsp.interacaoservice.domain.model.Comentario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class ComentarioEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String texto;
    private String linkComplementar;
    @Column(name = "midia_id")
    private Long midiaId;
    @Column(name = "autor_id")
    private Long autorId;
    private String nomeAutor;
    @Column(name = "comentario_pai_id")
    private Long comentarioPaiId;
    private LocalDateTime criadoEm;

    public ComentarioEntity() {}

    public Comentario toDomain() {
        return new Comentario(id, texto, linkComplementar, midiaId, autorId, nomeAutor, comentarioPaiId, criadoEm);
    }

    public static ComentarioEntity fromDomain(Comentario c) {
        ComentarioEntity e = new ComentarioEntity();
        e.id = c.getId(); e.texto = c.getTexto(); e.linkComplementar = c.getLinkComplementar();
        e.midiaId = c.getMidiaId(); e.autorId = c.getAutorId(); e.nomeAutor = c.getNomeAutor();
        e.comentarioPaiId = c.getComentarioPaiId(); e.criadoEm = c.getCriadoEm();
        return e;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public String getLinkComplementar() { return linkComplementar; }
    public void setLinkComplementar(String s) { this.linkComplementar = s; }
    public Long getMidiaId() { return midiaId; }
    public void setMidiaId(Long midiaId) { this.midiaId = midiaId; }
    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }
    public String getNomeAutor() { return nomeAutor; }
    public void setNomeAutor(String nomeAutor) { this.nomeAutor = nomeAutor; }
    public Long getComentarioPaiId() { return comentarioPaiId; }
    public void setComentarioPaiId(Long id) { this.comentarioPaiId = id; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
