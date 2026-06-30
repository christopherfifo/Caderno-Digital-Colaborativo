package br.edu.ifsp.midiaservice.infrastructure.persistence;
import br.edu.ifsp.midiaservice.domain.model.Midia;
import br.edu.ifsp.midiaservice.domain.repository.MidiaDomainRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MidiaDomainRepositoryImpl implements MidiaDomainRepository {
    private final MidiaJpaRepository jpaRepository;

    public MidiaDomainRepositoryImpl(MidiaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Midia salvar(Midia midia) {
        return jpaRepository.save(MidiaEntity.fromDomain(midia)).toDomain();
    }

    @Override
    public Optional<Midia> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(MidiaEntity::toDomain);
    }

    @Override
    public List<Midia> listarComFiltros(String disciplina, String professor, LocalDate data) {
        return jpaRepository.findComFiltros(disciplina, professor, data).stream()
            .map(MidiaEntity::toDomain).toList();
    }

    @Override
    public boolean existePorId(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void excluir(Long id) {
        jpaRepository.deleteById(id);
    }
}
