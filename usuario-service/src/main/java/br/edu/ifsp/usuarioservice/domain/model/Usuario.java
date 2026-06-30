package br.edu.ifsp.usuarioservice.domain.model;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipo;
    private Integer pontos;

    public Usuario() {}

    public Usuario(String nome, String email, TipoUsuario tipo) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.pontos = 0;
    }

    public Usuario(Long id, String nome, String email, TipoUsuario tipo, Integer pontos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.pontos = pontos;
    }

    public void adicionarPontos(int quantidade) { this.pontos += quantidade; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
    public Integer getPontos() { return pontos; }
    public void setPontos(Integer pontos) { this.pontos = pontos; }
}
