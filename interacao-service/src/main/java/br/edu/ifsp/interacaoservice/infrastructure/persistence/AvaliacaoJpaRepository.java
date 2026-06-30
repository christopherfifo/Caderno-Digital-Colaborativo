package br.edu.ifsp.interacaoservice.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoEntity, Long> {
    List<AvaliacaoEntity> findByMidiaId(Long midiaId);
    List<AvaliacaoEntity> findByComentarioId(Long comentarioId);
}
