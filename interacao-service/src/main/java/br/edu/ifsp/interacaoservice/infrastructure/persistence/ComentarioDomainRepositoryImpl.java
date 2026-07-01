package br.edu.ifsp.interacaoservice.infrastructure.persistence;
import br.edu.ifsp.interacaoservice.domain.model.Comentario;
import br.edu.ifsp.interacaoservice.domain.repository.ComentarioDomainRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ComentarioDomainRepositoryImpl implements ComentarioDomainRepository {
    private final ComentarioJpaRepository jpaRepository;

    public ComentarioDomainRepositoryImpl(ComentarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Comentario salvar(Comentario comentario) {
        return jpaRepository.save(ComentarioEntity.fromDomain(comentario)).toDomain();
    }

    @Override
    public Optional<Comentario> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(ComentarioEntity::toDomain);
    }

    @Override
    public List<Comentario> listarPorMidiaIdSemPai(Long midiaId) {
        return jpaRepository.findByMidiaIdAndComentarioPaiIdIsNullOrderByCriadoEmAsc(midiaId)
            .stream().map(ComentarioEntity::toDomain).toList();
    }

    @Override
    public List<Comentario> listarRespostas(Long comentarioPaiId) {
        return jpaRepository.findByComentarioPaiIdOrderByCriadoEmAsc(comentarioPaiId)
            .stream().map(ComentarioEntity::toDomain).toList();
    }
}
