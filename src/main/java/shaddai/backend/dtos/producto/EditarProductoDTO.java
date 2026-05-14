package shaddai.backend.dtos.producto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaddai.backend.entities.CategoriaEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarProductoDTO {
    private Categoria categoria;

    @NotBlank(message = "El nombre no puede ser vacío")
    private String nombre;

    @NotBlank(message = "La descripción no puede ser vacía")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.1", message = "El precio mínimo es S/ 0.10")
    private double precio;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser menor a 0")
    private Integer stock;

    @NotNull(message = "El estado no puede ser nulo")
    private Boolean activo;
}
