package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaddai.backend.dtos.categoria.CrearCategoriaDTO;
import shaddai.backend.dtos.categoria.CategoriaResponse;
import shaddai.backend.dtos.categoria.EditarCategoriaDTO;
import shaddai.backend.services.CategoriaService;

@RestController
@RequestMapping("/shaddai/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CrearCategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.crear(dto));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CategoriaResponse> editar (@Valid @RequestBody EditarCategoriaDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.editar(dto, id));
    }
}
