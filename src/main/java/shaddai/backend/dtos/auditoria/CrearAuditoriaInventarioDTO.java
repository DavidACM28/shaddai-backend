package shaddai.backend.dtos.auditoria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaddai.backend.utils.Accion;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearAuditoriaInventarioDTO {

    @NotNull(message = "La id del producto es obligatoria")
    private Long productoId;

    @NotNull(message = "La id del usuario es obligatoria")
    private Long usuarioId;

    @NotBlank(message = "La acción es obligatoria")
    private String accion;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotNull(message = "El stock antiguo es obligatorio")
    @Min(value = 0, message = "El stock antiguo no puede ser menor a 0")
    private Integer stockAntiguo;

    @NotNull(message = "El stock nuevo es obligatorio")
    @Min(value = 0, message = "El stock nuevo no puede ser menor a 0")
    private Integer stockNuevo;
}
