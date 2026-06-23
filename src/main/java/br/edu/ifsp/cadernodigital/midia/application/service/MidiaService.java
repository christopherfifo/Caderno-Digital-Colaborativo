package br.edu.ifsp.cadernodigital.midia.application.service;

import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.midia.application.dto.MidiaRequest;
import br.edu.ifsp.cadernodigital.midia.application.dto.MidiaResponse;
import br.edu.ifsp.cadernodigital.midia.application.gateway.PontuacaoGateway;
import br.edu.ifsp.cadernodigital.midia.application.gateway.UsuarioGateway;
import br.edu.ifsp.cadernodigital.midia.domain.model.Midia;
import br.edu.ifsp.cadernodigital.midia.domain.repository.MidiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MidiaService {
    private final MidiaRepository midiaRepository;
    private final UsuarioGateway usuarioGateway;
    private final PontuacaoGateway pontuacaoGateway;

    public MidiaService(MidiaRepository midiaRepository, UsuarioGateway usuarioGateway, PontuacaoGateway pontuacaoGateway) {
        this.midiaRepository = midiaRepository;
        this.usuarioGateway = usuarioGateway;
        this.pontuacaoGateway = pontuacaoGateway;
    }

    public MidiaResponse criar(MidiaRequest request) {
        usuarioGateway.validarAutorExistente(request.autorId());

        Midia midia = new Midia(
                request.titulo(),
                request.descricao(),
                request.urlArquivo(),
                request.tipo(),
                request.dataHoraAula(),
                request.disciplina(),
                request.professorResponsavel(),
                request.autorId()
        );

        Midia midiaSalva = midiaRepository.salvar(midia);
        pontuacaoGateway.pontuarEnvioDeMidia(request.autorId());

        String nomeAutor = usuarioGateway.buscarNomeAutor(request.autorId());
        return MidiaResponse.fromDomain(midiaSalva, nomeAutor);
    }

    public List<MidiaResponse> listar(String disciplina, String professor, java.time.LocalDate data) {
        List<Midia> midias;
        boolean temDisciplina = disciplina != null && !disciplina.isBlank();
        boolean temProfessor = professor != null && !professor.isBlank();

        if (temDisciplina || temProfessor) {
            midias = midiaRepository.buscarPorFiltros(
                    temDisciplina ? disciplina : null, 
                    temProfessor ? professor : null);
        } else {
            midias = midiaRepository.listarTodas();
        }

        return midias.stream()
                .filter(m -> data == null || (m.getDataHoraAula() != null && m.getDataHoraAula().toLocalDate().equals(data)))
                .map(m -> MidiaResponse.fromDomain(m, usuarioGateway.buscarNomeAutor(m.getAutorId())))
                .collect(Collectors.toList());
    }

    public MidiaResponse buscarPorId(Long id) {
        Midia midia = midiaRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
        return MidiaResponse.fromDomain(midia, usuarioGateway.buscarNomeAutor(midia.getAutorId()));
    }

    public MidiaResponse atualizar(Long id, MidiaRequest request) {
        Midia midia = midiaRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));

        midia.setTitulo(request.titulo());
        midia.setDescricao(request.descricao());
        midia.setUrlArquivo(request.urlArquivo());
        midia.setTipo(request.tipo());
        midia.setDataHoraAula(request.dataHoraAula());
        midia.setDisciplina(request.disciplina());
        midia.setProfessorResponsavel(request.professorResponsavel());

        Midia midiaAtualizada = midiaRepository.salvar(midia);
        return MidiaResponse.fromDomain(midiaAtualizada, usuarioGateway.buscarNomeAutor(midiaAtualizada.getAutorId()));
    }

    public void excluir(Long id) {
        Midia midia = midiaRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
        midiaRepository.excluir(midia);
    }
}
