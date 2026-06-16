package br.edu.ifsp.cadernodigital.model;

import br.edu.ifsp.cadernodigital.model.enums.TipoMidia;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "midias")
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String urlArquivo;

    @Enumerated(EnumType.STRING)
    private TipoMidia tipo;

    private LocalDateTime dataHoraAula;

    private String disciplina;

    private String professorResponsavel;

    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    public Midia() {
    }

    public Midia(String titulo, String descricao, String urlArquivo, TipoMidia tipo,
                 LocalDateTime dataHoraAula, String disciplina, String professorResponsavel,
                 Usuario autor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.urlArquivo = urlArquivo;
        this.tipo = tipo;
        this.dataHoraAula = dataHoraAula;
        this.disciplina = disciplina;
        this.professorResponsavel = professorResponsavel;
        this.autor = autor;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public TipoMidia getTipo() {
        return tipo;
    }

    public void setTipo(TipoMidia tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHoraAula() {
        return dataHoraAula;
    }

    public void setDataHoraAula(LocalDateTime dataHoraAula) {
        this.dataHoraAula = dataHoraAula;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(String professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
