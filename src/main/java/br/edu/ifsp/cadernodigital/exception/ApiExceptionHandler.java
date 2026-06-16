package br.edu.ifsp.cadernodigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  public ResponseEntity<ErroResponse> tratarNaoEncontrado(
    RecursoNaoEncontradoException exception
  ) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
      ErroResponse.criar(404, exception.getMessage())
    );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErroResponse> tratarRegraInvalida(
    IllegalArgumentException exception
  ) {
    return ResponseEntity.badRequest().body(
      ErroResponse.criar(400, exception.getMessage())
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErroResponse> tratarValidacao(
    MethodArgumentNotValidException exception
  ) {
    String mensagem = "Dados inválidos. Confira os campos obrigatórios.";
    return ResponseEntity.badRequest().body(ErroResponse.criar(400, mensagem));
  }
}
