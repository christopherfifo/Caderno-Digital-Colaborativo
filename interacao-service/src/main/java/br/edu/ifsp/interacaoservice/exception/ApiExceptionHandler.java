package br.edu.ifsp.interacaoservice.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErroResponse(mensagem, 400, LocalDateTime.now()));
    }
}
