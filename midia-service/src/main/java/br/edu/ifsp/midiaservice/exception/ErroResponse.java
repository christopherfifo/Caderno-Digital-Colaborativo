package br.edu.ifsp.midiaservice.exception;
import java.time.LocalDateTime;
public record ErroResponse(String mensagem, int status, LocalDateTime timestamp) {}
