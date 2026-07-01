package br.edu.ifsp.usuarioservice.infrastructure.persistence;

import br.edu.ifsp.usuarioservice.domain.model.TipoUsuario;
import br.edu.ifsp.usuarioservice.domain.model.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    public UsuarioEntity() {}

    public UsuarioEntity(Long id, String nome, String email, TipoUsuario tipo) {
        this.id = id; this.nome = nome; this.email = email; this.tipo = tipo;
    }

    public Usuario toDomain() {
        return new Usuario(id, nome, email, tipo);
    }

    public static UsuarioEntity fromDomain(Usuario u) {
        return new UsuarioEntity(u.getId(), u.getNome(), u.getEmail(), u.getTipo());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}
