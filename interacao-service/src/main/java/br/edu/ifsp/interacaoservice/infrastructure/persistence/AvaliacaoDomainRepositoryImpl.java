package br.edu.ifsp.interacaoservice.infrastructure.persistence;
import br.edu.ifsp.interacaoservice.domain.model.Avaliacao;
import br.edu.ifsp.interacaoservice.domain.repository.AvaliacaoDomainRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AvaliacaoDomainRepositoryImpl implements AvaliacaoDomainRepository {
    private final AvaliacaoJpaRepository jpaRepository;

    public AvaliacaoDomainRepositoryImpl(AvaliacaoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Avaliacao salvar(Avaliacao avaliacao) {
        return jpaRepository.save(AvaliacaoEntity.fromDomain(avaliacao)).toDomain();
    }

    @Override
    public List<Avaliacao> listarPorMidiaId(Long midiaId) {
        return jpaRepository.findByMidiaId(midiaId).stream().map(AvaliacaoEntity::toDomain).toList();
    }

    @Override
    public List<Avaliacao> listarPorComentarioId(Long comentarioId) {
        return jpaRepository.findByComentarioId(comentarioId).stream().map(AvaliacaoEntity::toDomain).toList();
    }
}
