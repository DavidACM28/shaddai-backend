package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaddai.backend.dtos.auditoria.AuditoriaInventarioResponse;
import shaddai.backend.dtos.auditoria.CrearAuditoriaInventarioDTO;
import shaddai.backend.entities.UsuarioEntity;
import shaddai.backend.services.AuditoriaInventarioService;

@RestController
@RequestMapping("/shaddai/api/auditoria")
public class AuditoriaInventarioController {

    @Autowired
    private AuditoriaInventarioService auditoriaInventarioService;

    @PostMapping("/crear")
    public ResponseEntity<AuditoriaInventarioResponse> crear(@Valid @RequestBody CrearAuditoriaInventarioDTO dto) {
        return ResponseEntity.ok().body(auditoriaInventarioService.crear(dto));
    }
}
