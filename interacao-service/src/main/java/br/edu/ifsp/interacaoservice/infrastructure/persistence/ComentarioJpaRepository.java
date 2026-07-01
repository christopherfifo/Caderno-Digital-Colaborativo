package br.edu.ifsp.interacaoservice.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ComentarioJpaRepository extends JpaRepository<ComentarioEntity, Long> {
    List<ComentarioEntity> findByMidiaIdAndComentarioPaiIdIsNullOrderByCriadoEmAsc(Long midiaId);
    List<ComentarioEntity> findByComentarioPaiIdOrderByCriadoEmAsc(Long comentarioPaiId);
}
