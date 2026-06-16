package br.edu.ifsp.cadernodigital.model;

import br.edu.ifsp.cadernodigital.model.enums.TipoAvaliacao;
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
@Table(name = "avaliacoes")
public class Avaliacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer nota;

  private String comentario;

  @Enumerated(EnumType.STRING)
  private TipoAvaliacao tipo;

  private LocalDateTime criadoEm;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "midia_id")
  private Midia midia;

  @ManyToOne
  @JoinColumn(name = "comentario_id")
  private Comentario comentarioAvaliado;

  public Avaliacao() {}

  public Avaliacao(
    Integer nota,
    String comentario,
    TipoAvaliacao tipo,
    Usuario usuario,
    Midia midia,
    Comentario comentarioAvaliado
  ) {
    this.nota = nota;
    this.comentario = comentario;
    this.tipo = tipo;
    this.usuario = usuario;
    this.midia = midia;
    this.comentarioAvaliado = comentarioAvaliado;
    this.criadoEm = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNota() {
    return nota;
  }

  public void setNota(Integer nota) {
    this.nota = nota;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public TipoAvaliacao getTipo() {
    return tipo;
  }

  public void setTipo(TipoAvaliacao tipo) {
    this.tipo = tipo;
  }

  public LocalDateTime getCriadoEm() {
    return criadoEm;
  }

  public void setCriadoEm(LocalDateTime criadoEm) {
    this.criadoEm = criadoEm;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Midia getMidia() {
    return midia;
  }

  public void setMidia(Midia midia) {
    this.midia = midia;
  }

  public Comentario getComentarioAvaliado() {
    return comentarioAvaliado;
  }

  public void setComentarioAvaliado(Comentario comentarioAvaliado) {
    this.comentarioAvaliado = comentarioAvaliado;
  }
}
