package br.edu.ifsp.usuarioservice.infrastructure.persistence;

import br.edu.ifsp.usuarioservice.domain.model.Usuario;
import br.edu.ifsp.usuarioservice.domain.repository.UsuarioDomainRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDomainRepositoryImpl implements UsuarioDomainRepository {
    private final UsuarioJpaRepository jpaRepository;

    public UsuarioDomainRepositoryImpl(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return jpaRepository.save(UsuarioEntity.fromDomain(usuario)).toDomain();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(UsuarioEntity::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email).map(UsuarioEntity::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpaRepository.findAll().stream().map(UsuarioEntity::toDomain).toList();
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
