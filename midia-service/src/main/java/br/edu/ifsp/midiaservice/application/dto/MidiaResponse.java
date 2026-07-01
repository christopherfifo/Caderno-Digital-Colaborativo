package br.edu.ifsp.midiaservice.application.dto;
import br.edu.ifsp.midiaservice.domain.model.Midia;
import br.edu.ifsp.midiaservice.domain.model.TipoMidia;
import java.time.LocalDateTime;
public record MidiaResponse(
    Long id, String titulo, String descricao, String urlArquivo, TipoMidia tipo,
    LocalDateTime dataHoraAula, String disciplina, String professorResponsavel,
    LocalDateTime criadoEm, Long autorId, String nomeAutor
) {
    public static MidiaResponse fromDomain(Midia m, String nomeAutor) {
        return new MidiaResponse(m.getId(), m.getTitulo(), m.getDescricao(), m.getUrlArquivo(),
            m.getTipo(), m.getDataHoraAula(), m.getDisciplina(), m.getProfessorResponsavel(),
            m.getCriadoEm(), m.getAutorId(), nomeAutor);
    }
}
