package br.edu.ifsp.midiaservice.domain.repository;
import br.edu.ifsp.midiaservice.domain.model.Midia;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface MidiaDomainRepository {
    Midia salvar(Midia midia);
    Optional<Midia> buscarPorId(Long id);
    List<Midia> listarComFiltros(String disciplina, String professor, LocalDate data);
    boolean existePorId(Long id);
    void excluir(Long id);
}
