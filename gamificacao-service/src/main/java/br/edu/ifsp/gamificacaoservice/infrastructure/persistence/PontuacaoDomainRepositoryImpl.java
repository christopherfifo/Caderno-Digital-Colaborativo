package br.edu.ifsp.gamificacaoservice.infrastructure.persistence;
import br.edu.ifsp.gamificacaoservice.domain.model.Pontuacao;
import br.edu.ifsp.gamificacaoservice.domain.repository.PontuacaoDomainRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PontuacaoDomainRepositoryImpl implements PontuacaoDomainRepository {
    private final PontuacaoJpaRepository jpaRepository;

    public PontuacaoDomainRepositoryImpl(PontuacaoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Pontuacao salvar(Pontuacao pontuacao) {
        return jpaRepository.save(PontuacaoEntity.fromDomain(pontuacao)).toDomain();
    }

    @Override
    public Optional<Pontuacao> buscarPorUsuarioId(Long usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).map(PontuacaoEntity::toDomain);
    }

    @Override
    public List<Pontuacao> listarOrdenadoPorPontosDesc() {
        return jpaRepository.findAllByOrderByPontosDesc().stream()
            .map(PontuacaoEntity::toDomain).toList();
    }

    @Override
    public Pontuacao obterOuCriar(Long usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId)
            .map(PontuacaoEntity::toDomain)
            .orElseGet(() -> salvar(new Pontuacao(usuarioId)));
    }
}
