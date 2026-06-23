package br.edu.ifsp.cadernodigital.midia.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MidiaJpaRepository extends JpaRepository<MidiaEntity, Long> {
    @org.springframework.data.jpa.repository.Query("SELECT m FROM MidiaEntity m WHERE " +
           "(:disciplina IS NULL OR LOWER(m.disciplina) LIKE LOWER(CONCAT('%', CAST(:disciplina AS string), '%'))) AND " +
           "(:professor IS NULL OR LOWER(m.professorResponsavel) LIKE LOWER(CONCAT('%', CAST(:professor AS string), '%')))")
    List<MidiaEntity> buscarPorFiltros(
            @org.springframework.data.repository.query.Param("disciplina") String disciplina, 
            @org.springframework.data.repository.query.Param("professor") String professor);
}
