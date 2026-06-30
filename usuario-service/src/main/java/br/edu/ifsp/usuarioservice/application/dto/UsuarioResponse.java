package br.edu.ifsp.usuarioservice.application.dto;

import br.edu.ifsp.usuarioservice.domain.model.TipoUsuario;
import br.edu.ifsp.usuarioservice.domain.model.Usuario;

public record UsuarioResponse(Long id, String nome, String email, TipoUsuario tipo, Integer pontos) {
    public static UsuarioResponse fromDomain(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getTipo(), u.getPontos());
    }
}
