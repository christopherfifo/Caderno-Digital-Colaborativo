package br.edu.ifsp.interacaoservice.application.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record ComentarioRequest(@NotBlank String texto, String linkComplementar, @NotNull Long autorId) {}
