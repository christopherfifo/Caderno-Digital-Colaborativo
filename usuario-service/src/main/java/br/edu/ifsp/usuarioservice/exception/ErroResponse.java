package br.edu.ifsp.usuarioservice.exception;

import java.time.LocalDateTime;

public record ErroResponse(String mensagem, int status, LocalDateTime timestamp) {}
