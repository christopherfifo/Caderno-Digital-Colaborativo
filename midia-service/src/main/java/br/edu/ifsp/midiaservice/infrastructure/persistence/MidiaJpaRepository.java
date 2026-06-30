package br.edu.ifsp.midiaservice.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
public interface MidiaJpaRepository extends JpaRepository<MidiaEntity, Long> {
    @Query("SELECT m FROM MidiaEntity m WHERE " +
           "(:disciplina IS NULL OR UPPER(m.disciplina) LIKE UPPER(CONCAT('%', :disciplina, '%'))) AND " +
           "(:professor IS NULL OR UPPER(m.professorResponsavel) LIKE UPPER(CONCAT('%', :professor, '%'))) AND " +
           "(:data IS NULL OR CAST(m.dataHoraAula AS date) = :data)")
    List<MidiaEntity> findComFiltros(@Param("disciplina") String disciplina,
                                      @Param("professor") String professor,
                                      @Param("data") LocalDate data);
}
