package br.edu.ifsp.interacaoservice.application.dto;
import br.edu.ifsp.interacaoservice.domain.model.Avaliacao;
import br.edu.ifsp.interacaoservice.domain.model.TipoAvaliacao;
import java.time.LocalDateTime;
public record AvaliacaoResponse(
    Long id, Integer nota, String comentario, TipoAvaliacao tipo,
    LocalDateTime criadoEm, Long usuarioId, Long midiaId, Long comentarioId
) {
    public static AvaliacaoResponse fromDomain(Avaliacao a) {
        return new AvaliacaoResponse(a.getId(), a.getNota(), a.getComentario(), a.getTipo(),
            a.getCriadoEm(), a.getUsuarioId(), a.getMidiaId(), a.getComentarioId());
    }
}
