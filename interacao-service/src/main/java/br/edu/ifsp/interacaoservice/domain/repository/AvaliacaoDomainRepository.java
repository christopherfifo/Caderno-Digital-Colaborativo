package br.edu.ifsp.interacaoservice.domain.repository;
import br.edu.ifsp.interacaoservice.domain.model.Avaliacao;
import java.util.List;
public interface AvaliacaoDomainRepository {
    Avaliacao salvar(Avaliacao avaliacao);
    List<Avaliacao> listarPorMidiaId(Long midiaId);
    List<Avaliacao> listarPorComentarioId(Long comentarioId);
}
