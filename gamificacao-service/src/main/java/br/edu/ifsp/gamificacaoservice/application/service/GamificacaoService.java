package br.edu.ifsp.gamificacaoservice.application.service;

import br.edu.ifsp.gamificacaoservice.application.dto.*;
import br.edu.ifsp.gamificacaoservice.application.gateway.UsuarioGateway;
import br.edu.ifsp.gamificacaoservice.domain.model.Pontuacao;
import br.edu.ifsp.gamificacaoservice.domain.repository.PontuacaoDomainRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GamificacaoService {
    private final PontuacaoDomainRepository pontuacaoRepository;
    private final UsuarioGateway usuarioGateway;

    public GamificacaoService(PontuacaoDomainRepository pontuacaoRepository, UsuarioGateway usuarioGateway) {
        this.pontuacaoRepository = pontuacaoRepository;
        this.usuarioGateway = usuarioGateway;
    }

    public PontuacaoResponse pontuar(PontuarRequest request) {
        Pontuacao pontuacao = pontuacaoRepository.obterOuCriar(request.usuarioId());
        pontuacao.adicionarPontos(request.tipoAcao().getPontos());
        return PontuacaoResponse.fromDomain(pontuacaoRepository.salvar(pontuacao));
    }

    public PontuacaoResponse buscarPontuacao(Long usuarioId) {
        return pontuacaoRepository.buscarPorUsuarioId(usuarioId)
            .map(PontuacaoResponse::fromDomain)
            .orElse(new PontuacaoResponse(usuarioId, 0));
    }

    public List<RankingItemResponse> listarRanking() {
        List<Pontuacao> pontuacoes = pontuacaoRepository.listarOrdenadoPorPontosDesc();
        List<RankingItemResponse> ranking = new ArrayList<>();
        for (int i = 0; i < pontuacoes.size(); i++) {
            Pontuacao p = pontuacoes.get(i);
            String nome;
            try { nome = usuarioGateway.buscarNomeUsuario(p.getUsuarioId()); }
            catch (Exception e) { nome = "Usuário Desconhecido"; }
            ranking.add(new RankingItemResponse(i + 1, p.getUsuarioId(), nome, p.getPontos()));
        }
        return ranking;
    }
}
