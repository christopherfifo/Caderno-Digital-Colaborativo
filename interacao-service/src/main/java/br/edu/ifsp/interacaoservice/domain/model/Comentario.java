package br.edu.ifsp.interacaoservice.domain.model;
import java.time.LocalDateTime;

public class Comentario {
    private Long id;
    private String texto;
    private String linkComplementar;
    private Long midiaId;
    private Long autorId;
    private String nomeAutor;
    private Long comentarioPaiId;
    private LocalDateTime criadoEm;

    public Comentario() {}

    public Comentario(String texto, String linkComplementar, Long midiaId, Long autorId, Long comentarioPaiId) {
        this.texto = texto;
        this.linkComplementar = linkComplementar;
        this.midiaId = midiaId;
        this.autorId = autorId;
        this.comentarioPaiId = comentarioPaiId;
        this.criadoEm = LocalDateTime.now();
    }

    public Comentario(Long id, String texto, String linkComplementar, Long midiaId, Long autorId,
                      String nomeAutor, Long comentarioPaiId, LocalDateTime criadoEm) {
        this.id = id; this.texto = texto; this.linkComplementar = linkComplementar;
        this.midiaId = midiaId; this.autorId = autorId; this.nomeAutor = nomeAutor;
        this.comentarioPaiId = comentarioPaiId; this.criadoEm = criadoEm;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public String getLinkComplementar() { return linkComplementar; }
    public void setLinkComplementar(String linkComplementar) { this.linkComplementar = linkComplementar; }
    public Long getMidiaId() { return midiaId; }
    public void setMidiaId(Long midiaId) { this.midiaId = midiaId; }
    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }
    public String getNomeAutor() { return nomeAutor; }
    public void setNomeAutor(String nomeAutor) { this.nomeAutor = nomeAutor; }
    public Long getComentarioPaiId() { return comentarioPaiId; }
    public void setComentarioPaiId(Long comentarioPaiId) { this.comentarioPaiId = comentarioPaiId; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
