package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.MaterialComplementar;
import br.edu.ifsp.cadernodigital.model.Midia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialComplementarRepository
  extends JpaRepository<MaterialComplementar, Long>
{
  List<MaterialComplementar> findByMidiaOrderByCriadoEmDesc(Midia midia);
}
