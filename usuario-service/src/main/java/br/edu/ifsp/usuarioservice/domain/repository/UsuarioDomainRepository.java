package br.edu.ifsp.usuarioservice.domain.repository;

import br.edu.ifsp.usuarioservice.domain.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioDomainRepository {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> listarTodos();
    boolean existePorId(Long id);
    void excluir(Long id);
}
