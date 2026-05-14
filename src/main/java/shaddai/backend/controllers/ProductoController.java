package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaddai.backend.dtos.producto.CrearProductoDTO;
import shaddai.backend.dtos.producto.EditarProductoDTO;
import shaddai.backend.entities.ProductoEntity;
import shaddai.backend.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/shaddai/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<ProductoEntity> crear(@Valid @RequestBody CrearProductoDTO dto) {
        return ResponseEntity.ok().body(productoService.crear(dto));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProductoEntity> editar(@PathVariable Long id, @Valid @RequestBody EditarProductoDTO dto) {
        return ResponseEntity.ok().body(productoService.editar(id, dto));
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoEntity>> findAllFilters(
            @RequestParam(required = false) Long productoId, @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String nombre, @RequestParam(required = false) Boolean activo) {
        return ResponseEntity.ok().body(productoService.findByFilters(productoId, categoriaId, nombre, activo));
    }
}
