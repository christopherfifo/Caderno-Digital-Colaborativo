package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Midia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MidiaRepository extends JpaRepository<Midia, Long> {

    List<Midia> findByDisciplinaContainingIgnoreCase(String disciplina);
}
