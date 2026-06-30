package br.edu.ifsp.gamificacaoservice.exception;
import java.time.LocalDateTime;
public record ErroResponse(String mensagem, int status, LocalDateTime timestamp) {}
