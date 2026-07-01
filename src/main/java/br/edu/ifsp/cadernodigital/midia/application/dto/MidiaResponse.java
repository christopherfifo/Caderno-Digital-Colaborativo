package br.edu.ifsp.cadernodigital.midia.application.dto;

import br.edu.ifsp.cadernodigital.midia.domain.model.Midia;
import br.edu.ifsp.cadernodigital.midia.domain.model.TipoMidia;
import java.time.LocalDateTime;

public record MidiaResponse(
        Long id,
        String titulo,
        String descricao,
        String urlArquivo,
        TipoMidia tipo,
        LocalDateTime dataHoraAula,
        String disciplina,
        String professorResponsavel,
        LocalDateTime criadoEm,
        Long autorId,
        String nomeAutor
) {
    public static MidiaResponse fromDomain(Midia midia, String nomeAutor) {
        return new MidiaResponse(
                midia.getId(),
                midia.getTitulo(),
                midia.getDescricao(),
                midia.getUrlArquivo(),
                midia.getTipo(),
                midia.getDataHoraAula(),
                midia.getDisciplina(),
                midia.getProfessorResponsavel(),
                midia.getCriadoEm(),
                midia.getAutorId(),
                nomeAutor
        );
    }
}
