package shaddai.backend.dtos.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoMasVendidoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private Long cantidadVendida;
}
