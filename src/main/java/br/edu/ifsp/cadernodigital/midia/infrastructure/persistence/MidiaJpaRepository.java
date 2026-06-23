package br.edu.ifsp.cadernodigital.midia.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MidiaJpaRepository extends JpaRepository<MidiaEntity, Long> {
    List<MidiaEntity> findByDisciplinaContainingIgnoreCase(String disciplina);
    List<MidiaEntity> findByProfessorResponsavelContainingIgnoreCase(String professor);
    List<MidiaEntity> findByDisciplinaContainingIgnoreCaseAndProfessorResponsavelContainingIgnoreCase(String disciplina, String professor);
}
