package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.Comentario;
import br.edu.ifsp.cadernodigital.midia.infrastructure.persistence.MidiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByMidiaAndComentarioPaiIsNullOrderByCriadoEmAsc(MidiaEntity midia);

    List<Comentario> findByComentarioPaiOrderByCriadoEmAsc(Comentario comentarioPai);
}
