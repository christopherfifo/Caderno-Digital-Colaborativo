package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.MaterialComplementarRequest;
import br.edu.ifsp.cadernodigital.dto.MaterialComplementarResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.MaterialComplementar;
import br.edu.ifsp.cadernodigital.model.Midia;
import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.repository.MaterialComplementarRepository;
import br.edu.ifsp.cadernodigital.repository.MidiaRepository;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
import br.edu.ifsp.cadernodigital.service.PontuacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/midias/{midiaId}/materiais")
public class MaterialComplementarController {

    private final MaterialComplementarRepository materialRepository;
    private final MidiaRepository midiaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PontuacaoService pontuacaoService;

    public MaterialComplementarController(MaterialComplementarRepository materialRepository,
                                          MidiaRepository midiaRepository,
                                          UsuarioRepository usuarioRepository,
                                          PontuacaoService pontuacaoService) {
        this.materialRepository = materialRepository;
        this.midiaRepository = midiaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pontuacaoService = pontuacaoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialComplementarResponse criar(@PathVariable Long midiaId,
                                              @RequestBody @Valid MaterialComplementarRequest request) {
        Midia midia = buscarMidia(midiaId);
        Usuario autor = buscarUsuario(request.autorId());

        MaterialComplementar material = new MaterialComplementar(
                request.titulo(),
                request.descricao(),
                request.link(),
                midia,
                autor
        );

        MaterialComplementar materialSalvo = materialRepository.save(material);
        pontuacaoService.pontuarMaterialComplementar(autor);

        return MaterialComplementarResponse.fromEntity(materialSalvo);
    }

    @GetMapping
    public List<MaterialComplementarResponse> listar(@PathVariable Long midiaId) {
        Midia midia = buscarMidia(midiaId);

        return materialRepository.findByMidiaOrderByCriadoEmDesc(midia)
                .stream()
                .map(MaterialComplementarResponse::fromEntity)
                .toList();
    }

    private Midia buscarMidia(Long id) {
        return midiaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mídia não encontrada."));
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
    }
}
