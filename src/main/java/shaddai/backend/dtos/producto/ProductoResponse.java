package shaddai.backend.dtos.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaddai.backend.dtos.categoria.CategoriaResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {
    private Long id;
    private CategoriaResponse categoria;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private boolean activo;
}
