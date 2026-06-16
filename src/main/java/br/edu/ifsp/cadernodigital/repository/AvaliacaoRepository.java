package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Avaliacao;
import br.edu.ifsp.cadernodigital.model.Comentario;
import br.edu.ifsp.cadernodigital.model.Midia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
  List<Avaliacao> findByMidia(Midia midia);

  List<Avaliacao> findByComentarioAvaliado(Comentario comentarioAvaliado);
}
