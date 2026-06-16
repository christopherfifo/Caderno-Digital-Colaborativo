package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Midia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidiaRepository extends JpaRepository<Midia, Long> {
  List<Midia> findByDisciplinaNomeContainingIgnoreCase(String disciplina);

  List<Midia> findByAulaProfessorNomeContainingIgnoreCase(String professor);

  List<
    Midia
  > findByDisciplinaNomeContainingIgnoreCaseAndAulaProfessorNomeContainingIgnoreCase(
    String disciplina,
    String professor
  );
}
