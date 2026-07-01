package br.edu.ifsp.midiaservice.infrastructure.persistence;
import br.edu.ifsp.midiaservice.domain.model.Midia;
import br.edu.ifsp.midiaservice.domain.model.TipoMidia;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "midias")
public class MidiaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "autor_id")
    private Long autorId;

    public MidiaEntity() {}

    public Midia toDomain() {
        return new Midia(id, titulo, descricao, urlArquivo, tipo, dataHoraAula, disciplina, professorResponsavel, criadoEm, autorId);
    }

    public static MidiaEntity fromDomain(Midia m) {
        MidiaEntity e = new MidiaEntity();
        e.id = m.getId(); e.titulo = m.getTitulo(); e.descricao = m.getDescricao();
        e.urlArquivo = m.getUrlArquivo(); e.tipo = m.getTipo(); e.dataHoraAula = m.getDataHoraAula();
        e.disciplina = m.getDisciplina(); e.professorResponsavel = m.getProfessorResponsavel();
        e.criadoEm = m.getCriadoEm(); e.autorId = m.getAutorId();
        return e;
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
