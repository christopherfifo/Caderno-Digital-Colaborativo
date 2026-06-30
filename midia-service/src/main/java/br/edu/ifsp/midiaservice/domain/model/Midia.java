package br.edu.ifsp.midiaservice.domain.model;
import java.time.LocalDateTime;
public class Midia {
    private Long id;
    private String titulo;
    private String descricao;
    private String urlArquivo;
    private TipoMidia tipo;
    private LocalDateTime dataHoraAula;
    private String disciplina;
    private String professorResponsavel;
    private LocalDateTime criadoEm;
    private Long autorId;

    public Midia() {}

    public Midia(String titulo, String descricao, String urlArquivo, TipoMidia tipo,
                 LocalDateTime dataHoraAula, String disciplina, String professorResponsavel, Long autorId) {
        this.titulo = titulo; this.descricao = descricao; this.urlArquivo = urlArquivo;
        this.tipo = tipo; this.dataHoraAula = dataHoraAula; this.disciplina = disciplina;
        this.professorResponsavel = professorResponsavel; this.autorId = autorId;
        this.criadoEm = LocalDateTime.now();
    }

    public Midia(Long id, String titulo, String descricao, String urlArquivo, TipoMidia tipo,
                 LocalDateTime dataHoraAula, String disciplina, String professorResponsavel,
                 LocalDateTime criadoEm, Long autorId) {
        this.id = id; this.titulo = titulo; this.descricao = descricao; this.urlArquivo = urlArquivo;
        this.tipo = tipo; this.dataHoraAula = dataHoraAula; this.disciplina = disciplina;
        this.professorResponsavel = professorResponsavel; this.criadoEm = criadoEm; this.autorId = autorId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getUrlArquivo() { return urlArquivo; }
    public void setUrlArquivo(String urlArquivo) { this.urlArquivo = urlArquivo; }
    public TipoMidia getTipo() { return tipo; }
    public void setTipo(TipoMidia tipo) { this.tipo = tipo; }
    public LocalDateTime getDataHoraAula() { return dataHoraAula; }
    public void setDataHoraAula(LocalDateTime dataHoraAula) { this.dataHoraAula = dataHoraAula; }
    public String getDisciplina() { return disciplina; }
    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }
    public String getProfessorResponsavel() { return professorResponsavel; }
    public void setProfessorResponsavel(String professorResponsavel) { this.professorResponsavel = professorResponsavel; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }
}
