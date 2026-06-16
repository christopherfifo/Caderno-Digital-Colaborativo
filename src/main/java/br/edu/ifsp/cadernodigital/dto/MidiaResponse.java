package br.edu.ifsp.cadernodigital.dto;

import br.edu.ifsp.cadernodigital.model.Midia;
import br.edu.ifsp.cadernodigital.model.enums.TipoMidia;

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
    public static MidiaResponse fromEntity(Midia midia) {
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
                midia.getAutor().getId(),
                midia.getAutor().getNome()
        );
    }
}
