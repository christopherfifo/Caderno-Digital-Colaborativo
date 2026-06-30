package br.edu.ifsp.gamificacaoservice.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleNotFound(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErroResponse(ex.getMessage(), 404, LocalDateTime.now()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErroResponse(ex.getMessage(), 400, LocalDateTime.now()));
    }
}
