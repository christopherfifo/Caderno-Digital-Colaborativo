package br.edu.ifsp.usuarioservice.presentation;

import br.edu.ifsp.usuarioservice.application.dto.InternalUsuarioResponse;
import br.edu.ifsp.usuarioservice.application.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/usuarios")
public class UsuarioInternalController {
    private final UsuarioService usuarioService;

    public UsuarioInternalController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public InternalUsuarioResponse buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarInternoPorId(id);
    }

    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        boolean existe = usuarioService.existePorId(id);
        if (existe) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
