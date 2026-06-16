package br.edu.ifsp.cadernodigital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private String linkComplementar;

    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "midia_id")
    private Midia midia;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "comentario_pai_id")
    private Comentario comentarioPai;

    public Comentario() {
    }

    public Comentario(String texto, String linkComplementar, Midia midia, Usuario autor, Comentario comentarioPai) {
        this.texto = texto;
        this.linkComplementar = linkComplementar;
        this.midia = midia;
        this.autor = autor;
        this.comentarioPai = comentarioPai;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getLinkComplementar() {
        return linkComplementar;
    }

    public void setLinkComplementar(String linkComplementar) {
        this.linkComplementar = linkComplementar;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
    }
}
