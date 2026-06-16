package br.edu.ifsp.cadernodigital.model;

import br.edu.ifsp.cadernodigital.model.enums.TipoUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String email;

  @Enumerated(EnumType.STRING)
  private TipoUsuario tipo;

  private Integer pontos = 0;

  public Usuario() {}

  public Usuario(String nome, String email, TipoUsuario tipo) {
    this.nome = nome;
    this.email = email;
    this.tipo = tipo;
    this.pontos = 0;
  }

  public void adicionarPontos(int quantidade) {
    this.pontos += quantidade;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public TipoUsuario getTipo() {
    return tipo;
  }

  public void setTipo(TipoUsuario tipo) {
    this.tipo = tipo;
  }

  public Integer getPontos() {
    return pontos;
  }

  public void setPontos(Integer pontos) {
    this.pontos = pontos;
  }
}
