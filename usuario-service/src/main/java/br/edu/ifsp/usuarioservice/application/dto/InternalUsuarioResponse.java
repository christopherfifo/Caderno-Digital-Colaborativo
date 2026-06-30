package br.edu.ifsp.usuarioservice.application.dto;

import br.edu.ifsp.usuarioservice.domain.model.TipoUsuario;
import br.edu.ifsp.usuarioservice.domain.model.Usuario;

public record InternalUsuarioResponse(Long id, String nome, TipoUsuario tipo) {
    public static InternalUsuarioResponse fromDomain(Usuario u) {
        return new InternalUsuarioResponse(u.getId(), u.getNome(), u.getTipo());
    }
}
