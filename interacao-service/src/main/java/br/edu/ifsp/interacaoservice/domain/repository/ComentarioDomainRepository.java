package br.edu.ifsp.interacaoservice.domain.repository;
import br.edu.ifsp.interacaoservice.domain.model.Comentario;
import java.util.List;
import java.util.Optional;
public interface ComentarioDomainRepository {
    Comentario salvar(Comentario comentario);
    Optional<Comentario> buscarPorId(Long id);
    List<Comentario> listarPorMidiaIdSemPai(Long midiaId);
    List<Comentario> listarRespostas(Long comentarioPaiId);
}
