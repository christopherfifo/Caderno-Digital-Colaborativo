package br.edu.ifsp.cadernodigital.midia.domain.repository;

import br.edu.ifsp.cadernodigital.midia.domain.model.Midia;
import java.util.List;
import java.util.Optional;

public interface MidiaRepository {
    Midia salvar(Midia midia);
    Optional<Midia> buscarPorId(Long id);
    List<Midia> listarTodas();
    List<Midia> buscarPorFiltros(String disciplina, String professor);
    void excluir(Midia midia);
}
