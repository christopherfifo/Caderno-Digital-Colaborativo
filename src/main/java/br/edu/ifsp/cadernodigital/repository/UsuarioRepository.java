package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByOrderByPontosDesc();

    @Query("SELECT u FROM Usuario u " +
           "WHERE u.id IN (SELECT m.autor.id FROM Midia m WHERE UPPER(m.disciplina) LIKE UPPER(CONCAT('%', :disciplina, '%'))) " +
           "OR u.id IN (SELECT c.autor.id FROM Comentario c WHERE UPPER(c.midia.disciplina) LIKE UPPER(CONCAT('%', :disciplina, '%'))) " +
           "ORDER BY u.pontos DESC")
    List<Usuario> findRankingByDisciplina(@Param("disciplina") String disciplina);
}
