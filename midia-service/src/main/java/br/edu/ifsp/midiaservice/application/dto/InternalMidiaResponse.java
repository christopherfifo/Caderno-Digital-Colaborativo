package br.edu.ifsp.midiaservice.application.dto;
import br.edu.ifsp.midiaservice.domain.model.Midia;
public record InternalMidiaResponse(Long id, String disciplina, Long autorId) {
    public static InternalMidiaResponse fromDomain(Midia m) {
        return new InternalMidiaResponse(m.getId(), m.getDisciplina(), m.getAutorId());
    }
}
