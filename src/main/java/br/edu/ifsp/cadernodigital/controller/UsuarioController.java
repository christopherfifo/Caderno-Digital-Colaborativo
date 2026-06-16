package br.edu.ifsp.cadernodigital.controller;

import br.edu.ifsp.cadernodigital.dto.RankingResponse;
import br.edu.ifsp.cadernodigital.dto.UsuarioRequest;
import br.edu.ifsp.cadernodigital.dto.UsuarioResponse;
import br.edu.ifsp.cadernodigital.exception.RecursoNaoEncontradoException;
import br.edu.ifsp.cadernodigital.model.Usuario;
import br.edu.ifsp.cadernodigital.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse criar(@RequestBody @Valid UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }

        Usuario usuario = new Usuario(request.nome(), request.email(), request.tipo());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioResponse.fromEntity(usuarioSalvo);
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponse buscarPorId(@PathVariable Long id) {
        Usuario usuario = buscarUsuario(id);
        return UsuarioResponse.fromEntity(usuario);
    }

    @GetMapping("/ranking")
    public List<RankingResponse> ranking() {
        List<Usuario> usuarios = usuarioRepository.findAllByOrderByPontosDesc();
        List<RankingResponse> ranking = new ArrayList<>();

        for (int i = 0; i < usuarios.size(); i++) {
            ranking.add(RankingResponse.fromEntity(i + 1, usuarios.get(i)));
        }

        return ranking;
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
    }
}
