package br.edu.ifsp.cadernodigital.service;

import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PontuacaoService {

  private final UsuarioRepository usuarioRepository;

  public PontuacaoService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public void pontuarEnvioDeMidia(Usuario usuario) {
    adicionarPontos(usuario, 10);
  }

  public void pontuarComentario(Usuario usuario) {
    adicionarPontos(usuario, 5);
  }

  public void pontuarMaterialComplementar(Usuario usuario) {
    adicionarPontos(usuario, 7);
  }

  public void pontuarAvaliacao(Usuario usuario) {
    adicionarPontos(usuario, 2);
  }

  public void pontuarContribuicaoBemAvaliada(Usuario usuario) {
    adicionarPontos(usuario, 3);
  }

  private void adicionarPontos(Usuario usuario, int pontos) {
    usuario.adicionarPontos(pontos);
    usuarioRepository.save(usuario);
  }
}
