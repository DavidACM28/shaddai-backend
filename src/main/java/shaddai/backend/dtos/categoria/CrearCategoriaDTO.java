package shaddai.backend.dtos.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearCategoriaDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
