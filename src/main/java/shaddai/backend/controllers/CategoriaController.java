package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaddai.backend.dtos.categoria.CrearCategoriaDTO;
import shaddai.backend.dtos.categoria.CrearCategoriaResponse;
import shaddai.backend.services.CategoriaService;

@RestController
@RequestMapping("/shaddai/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/crear")
    public ResponseEntity<CrearCategoriaResponse> crear(@Valid @RequestBody CrearCategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.crear(dto));
    }

}
