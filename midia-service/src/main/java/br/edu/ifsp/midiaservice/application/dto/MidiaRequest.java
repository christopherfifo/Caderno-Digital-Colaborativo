package br.edu.ifsp.midiaservice.application.dto;
import br.edu.ifsp.midiaservice.domain.model.TipoMidia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
public record MidiaRequest(
    @NotBlank String titulo,
    String descricao,
    @NotBlank String urlArquivo,
    @NotNull TipoMidia tipo,
    @NotNull LocalDateTime dataHoraAula,
    @NotBlank String disciplina,
    @NotBlank String professorResponsavel,
    @NotNull Long autorId
) {}
