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
@Table(name = "aulas")
public class Aula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  private String descricao;

  private LocalDateTime dataHora;

  @ManyToOne
  @JoinColumn(name = "disciplina_id", nullable = false)
  private Disciplina disciplina;

  @ManyToOne
  @JoinColumn(name = "professor_id", nullable = false)
  private Usuario professor;

  public Aula() {}

  public Aula(
    String titulo,
    String descricao,
    LocalDateTime dataHora,
    Disciplina disciplina,
    Usuario professor
  ) {
    this.titulo = titulo;
    this.descricao = descricao;
    this.dataHora = dataHora;
    this.disciplina = disciplina;
    this.professor = professor;
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

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }

  public Usuario getProfessor() {
    return professor;
  }

  public void setProfessor(Usuario professor) {
    this.professor = professor;
  }
}
