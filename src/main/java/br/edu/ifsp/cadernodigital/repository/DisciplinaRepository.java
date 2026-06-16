package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {}
