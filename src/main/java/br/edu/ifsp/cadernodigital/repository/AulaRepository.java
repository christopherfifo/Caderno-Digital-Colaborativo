package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Aula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
  List<Aula> findByDisciplinaId(Long disciplinaId);
  List<Aula> findByProfessorId(Long professorId);
}
