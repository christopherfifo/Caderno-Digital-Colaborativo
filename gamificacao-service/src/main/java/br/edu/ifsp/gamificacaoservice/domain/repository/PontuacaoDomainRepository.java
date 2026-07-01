package br.edu.ifsp.gamificacaoservice.domain.repository;

import br.edu.ifsp.gamificacaoservice.domain.model.Pontuacao;
import java.util.List;
import java.util.Optional;

public interface PontuacaoDomainRepository {
    Pontuacao salvar(Pontuacao pontuacao);
    Optional<Pontuacao> buscarPorUsuarioId(Long usuarioId);
    List<Pontuacao> listarOrdenadoPorPontosDesc();
    Pontuacao obterOuCriar(Long usuarioId);
}
