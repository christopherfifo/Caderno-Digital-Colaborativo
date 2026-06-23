package br.edu.ifsp.cadernodigital.midia.infrastructure.persistence;

import br.edu.ifsp.cadernodigital.midia.domain.model.Midia;
import br.edu.ifsp.cadernodigital.midia.domain.repository.MidiaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MidiaRepositoryImpl implements MidiaRepository {

    private final MidiaJpaRepository jpaRepository;

    public MidiaRepositoryImpl(MidiaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Midia salvar(Midia midia) {
        MidiaEntity entity = toEntity(midia);
        MidiaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Midia> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Midia> listarTodas() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Midia> buscarPorDisciplina(String disciplina) {
        return jpaRepository.findByDisciplinaContainingIgnoreCase(disciplina).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Midia> buscarPorProfessor(String professor) {
        return jpaRepository.findByProfessorResponsavelContainingIgnoreCase(professor).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Midia> buscarPorDisciplinaEProfessor(String disciplina, String professor) {
        return jpaRepository.findByDisciplinaContainingIgnoreCaseAndProfessorResponsavelContainingIgnoreCase(disciplina, professor).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void excluir(Midia midia) {
        jpaRepository.deleteById(midia.getId());
    }

    private MidiaEntity toEntity(Midia midia) {
        MidiaEntity entity = new MidiaEntity();
        entity.setId(midia.getId());
        entity.setTitulo(midia.getTitulo());
        entity.setDescricao(midia.getDescricao());
        entity.setUrlArquivo(midia.getUrlArquivo());
        entity.setTipo(midia.getTipo());
        entity.setDataHoraAula(midia.getDataHoraAula());
        entity.setDisciplina(midia.getDisciplina());
        entity.setProfessorResponsavel(midia.getProfessorResponsavel());
        entity.setCriadoEm(midia.getCriadoEm());
        entity.setAutorId(midia.getAutorId());
        return entity;
    }

    private Midia toDomain(MidiaEntity entity) {
        return new Midia(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getUrlArquivo(),
                entity.getTipo(),
                entity.getDataHoraAula(),
                entity.getDisciplina(),
                entity.getProfessorResponsavel(),
                entity.getCriadoEm(),
                entity.getAutorId()
        );
    }
}
