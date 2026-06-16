package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.model.enums.TipoUsuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        TipoUsuario tipo,
        Integer pontos
) {
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipo(),
                usuario.getPontos()
        );
    }
}
