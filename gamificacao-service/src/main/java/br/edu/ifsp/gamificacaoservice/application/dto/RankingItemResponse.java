package br.edu.ifsp.gamificacaoservice.application.dto;

public record RankingItemResponse(int posicao, Long usuarioId, String nomeUsuario, Integer pontos) {}
