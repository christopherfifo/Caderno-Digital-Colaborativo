package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Comentario;
import br.edu.ifsp.cadernodigital.model.Midia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByMidiaAndComentarioPaiIsNullOrderByCriadoEmAsc(Midia midia);

    List<Comentario> findByComentarioPaiOrderByCriadoEmAsc(Comentario comentarioPai);
}
