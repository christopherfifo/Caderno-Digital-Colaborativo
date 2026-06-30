package br.edu.ifsp.gamificacaoservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PontuacaoJpaRepository extends JpaRepository<PontuacaoEntity, Long> {
    Optional<PontuacaoEntity> findByUsuarioId(Long usuarioId);
    List<PontuacaoEntity> findAllByOrderByPontosDesc();
}
