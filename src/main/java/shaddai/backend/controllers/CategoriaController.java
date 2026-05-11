package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaddai.backend.dtos.categoria.CategoriaResponse;
import shaddai.backend.dtos.categoria.CrearCategoriaDTO;
import shaddai.backend.dtos.categoria.EditarCategoriaDTO;
import shaddai.backend.entities.CategoriaEntity;
import shaddai.backend.services.CategoriaService;

import java.util.List;

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
    public ResponseEntity<CategoriaResponse> editar(@Valid @RequestBody EditarCategoriaDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.editar(dto, id));
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaEntity>> findAll() {
        return ResponseEntity.ok().body(categoriaService.findAll());
    }

    @GetMapping("/categorias/activas")
    public ResponseEntity<List<CategoriaEntity>> activas() {
        return ResponseEntity.ok().body(categoriaService.findAllActivas());
    }

    @GetMapping("/categorias/inactivas")
    public ResponseEntity<List<CategoriaEntity>> inactivas() {
        return ResponseEntity.ok().body(categoriaService.findAllInactivas());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categorias/{page}")
    public ResponseEntity<Page<CategoriaEntity>> findAll(@PathVariable int page) {
        final PageRequest pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(categoriaService.findAllPage(pageable));
    }
}
