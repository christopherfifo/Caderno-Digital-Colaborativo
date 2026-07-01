package br.edu.ifsp.usuarioservice.application.dto;

import br.edu.ifsp.usuarioservice.domain.model.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
    @NotBlank String nome,
    @NotBlank @Email String email,
    @NotNull TipoUsuario tipo
) {}
