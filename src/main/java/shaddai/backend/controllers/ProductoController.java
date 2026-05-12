package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaddai.backend.dtos.producto.CrearProductoDTO;
import shaddai.backend.entities.ProductoEntity;
import shaddai.backend.services.ProductoService;
import shaddai.backend.services.UsuarioService;

@RestController
@RequestMapping("/shaddai/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<ProductoEntity> crear(@Valid @RequestBody CrearProductoDTO dto) {
        return ResponseEntity.ok().body(productoService.crear(dto));
    }
}
