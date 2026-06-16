package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
  @NotBlank String nome,
  @NotBlank @Email String email,
  @NotNull TipoUsuario tipo
) {}
