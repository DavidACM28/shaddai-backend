package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaddai.backend.dtos.producto.CrearProductoDTO;
import shaddai.backend.dtos.producto.EditarProductoDTO;
import shaddai.backend.dtos.producto.ProductoMasVendidoDTO;
import shaddai.backend.dtos.producto.ProductoResponse;
import shaddai.backend.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/shaddai/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody CrearProductoDTO dto) {
        return ResponseEntity.ok().body(productoService.crear(dto));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProductoResponse> editar(@PathVariable Long id, @Valid @RequestBody EditarProductoDTO dto) {
        return ResponseEntity.ok().body(productoService.editar(id, dto));
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoResponse>> findAllFilters(
            @RequestParam(required = false) Long productoId, @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String nombre, @RequestParam(required = false) Boolean activo) {
        return ResponseEntity.ok().body(productoService.findByFilters(productoId, categoriaId, nombre, activo));
    }

    @GetMapping("/mas-vendidos")
    public ResponseEntity<List<ProductoMasVendidoDTO>> findProductosMasVendidos() {
        return ResponseEntity.ok().body(productoService.findProductosMasVendidos());
    }

    @PatchMapping("/activar/{id}")
    public ResponseEntity<ProductoResponse> activar(@PathVariable Long id) {
        return ResponseEntity.ok().body(productoService.activar(id));
    }

    @PatchMapping("/desactivar/{id}")
    public ResponseEntity<ProductoResponse> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok().body(productoService.desactivar(id));
    }
}
