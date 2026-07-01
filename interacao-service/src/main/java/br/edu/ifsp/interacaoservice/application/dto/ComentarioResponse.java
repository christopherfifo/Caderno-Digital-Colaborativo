package br.edu.ifsp.interacaoservice.application.dto;
import br.edu.ifsp.interacaoservice.domain.model.Comentario;
import java.time.LocalDateTime;
public record ComentarioResponse(
    Long id, String texto, String linkComplementar, LocalDateTime criadoEm,
    Long midiaId, Long autorId, String nomeAutor, Long comentarioPaiId
) {
    public static ComentarioResponse fromDomain(Comentario c) {
        return new ComentarioResponse(c.getId(), c.getTexto(), c.getLinkComplementar(),
            c.getCriadoEm(), c.getMidiaId(), c.getAutorId(), c.getNomeAutor(), c.getComentarioPaiId());
    }
}
