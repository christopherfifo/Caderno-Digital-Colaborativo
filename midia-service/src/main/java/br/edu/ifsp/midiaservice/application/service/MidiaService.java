package br.edu.ifsp.midiaservice.application.service;
import br.edu.ifsp.midiaservice.application.dto.*;
import br.edu.ifsp.midiaservice.application.gateway.GamificacaoGateway;
import br.edu.ifsp.midiaservice.application.gateway.UsuarioGateway;
import br.edu.ifsp.midiaservice.domain.model.Midia;
import br.edu.ifsp.midiaservice.domain.repository.MidiaDomainRepository;
import br.edu.ifsp.midiaservice.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MidiaService {
    private final MidiaDomainRepository midiaRepository;
    private final UsuarioGateway usuarioGateway;
    private final GamificacaoGateway gamificacaoGateway;

    public MidiaService(MidiaDomainRepository midiaRepository, UsuarioGateway usuarioGateway, GamificacaoGateway gamificacaoGateway) {
        this.midiaRepository = midiaRepository;
        this.usuarioGateway = usuarioGateway;
        this.gamificacaoGateway = gamificacaoGateway;
    }

    public MidiaResponse criar(MidiaRequest request) {
        usuarioGateway.validarAutorExistente(request.autorId());
        Midia midia = new Midia(request.titulo(), request.descricao(), request.urlArquivo(),
            request.tipo(), request.dataHoraAula(), request.disciplina(),
            request.professorResponsavel(), request.autorId());
        Midia salva = midiaRepository.salvar(midia);
        try { gamificacaoGateway.pontuarEnvioDeMidia(request.autorId()); } catch (Exception ignored) {}
        String nomeAutor = usuarioGateway.buscarNomeAutor(request.autorId());
        return MidiaResponse.fromDomain(salva, nomeAutor);
    }

    public List<MidiaResponse> listar(String disciplina, String professor, LocalDate data) {
        return midiaRepository.listarComFiltros(disciplina, professor, data).stream()
            .map(m -> MidiaResponse.fromDomain(m, usuarioGateway.buscarNomeAutor(m.getAutorId())))
            .toList();
    }

    public MidiaResponse buscarPorId(Long id) {
        Midia midia = midiaRepository.buscarPorId(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
        return MidiaResponse.fromDomain(midia, usuarioGateway.buscarNomeAutor(midia.getAutorId()));
    }

    public MidiaResponse atualizar(Long id, MidiaRequest request) {
        Midia midia = midiaRepository.buscarPorId(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
        usuarioGateway.validarAutorExistente(request.autorId());
        midia.setTitulo(request.titulo());
        midia.setDescricao(request.descricao());
        midia.setUrlArquivo(request.urlArquivo());
        midia.setTipo(request.tipo());
        midia.setDataHoraAula(request.dataHoraAula());
        midia.setDisciplina(request.disciplina());
        midia.setProfessorResponsavel(request.professorResponsavel());
        midia.setAutorId(request.autorId());
        Midia atualizada = midiaRepository.salvar(midia);
        return MidiaResponse.fromDomain(atualizada, usuarioGateway.buscarNomeAutor(atualizada.getAutorId()));
    }

    public void excluir(Long id) {
        if (!midiaRepository.existePorId(id)) throw new RecursoNaoEncontradoException("Mídia não encontrada.");
        midiaRepository.excluir(id);
    }

    public InternalMidiaResponse buscarInternoPorId(Long id) {
        Midia midia = midiaRepository.buscarPorId(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
        return InternalMidiaResponse.fromDomain(midia);
    }
}
