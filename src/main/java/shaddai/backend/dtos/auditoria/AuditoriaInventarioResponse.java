package shaddai.backend.dtos.auditoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaddai.backend.dtos.UsuarioDTO;
import shaddai.backend.dtos.producto.ProductoResponse;
import shaddai.backend.utils.Accion;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaInventarioResponse {
    private Long id;
    private ProductoResponse producto;
    private UsuarioDTO usuario;
    private Accion accion;
    private String mensaje;
    private int stockAntiguo;
    private int stockNuevo;
    private LocalDate fecha;
    private LocalTime hora;
}
