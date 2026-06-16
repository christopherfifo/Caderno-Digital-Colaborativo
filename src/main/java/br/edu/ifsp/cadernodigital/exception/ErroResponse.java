package br.edu.ifsp.cadernodigital.exception;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime dataHora,
        int status,
        String erro
) {
    public static ErroResponse criar(int status, String erro) {
        return new ErroResponse(LocalDateTime.now(), status, erro);
    }
}
