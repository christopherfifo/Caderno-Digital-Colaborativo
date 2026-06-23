package br.edu.ifsp.cadernodigital.repository;

import br.edu.ifsp.cadernodigital.model.MaterialComplementar;
import br.edu.ifsp.cadernodigital.midia.infrastructure.persistence.MidiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialComplementarRepository extends JpaRepository<MaterialComplementar, Long> {

    List<MaterialComplementar> findByMidiaOrderByCriadoEmDesc(MidiaEntity midia);
}
