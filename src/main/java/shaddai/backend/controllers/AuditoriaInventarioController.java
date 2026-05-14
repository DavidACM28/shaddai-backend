package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaddai.backend.dtos.auditoria.AuditoriaInventarioResponse;
import shaddai.backend.dtos.auditoria.CrearAuditoriaInventarioDTO;
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

    @GetMapping("/auditorias")
    public ResponseEntity<Page<AuditoriaInventarioResponse>> findAllFilters(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "8", required = false) Integer size, @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipo, @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta) {
        return ResponseEntity.ok().body(auditoriaInventarioService.findAll(page, size, nombre, tipo, fechaDesde, fechaHasta));
    }
}
