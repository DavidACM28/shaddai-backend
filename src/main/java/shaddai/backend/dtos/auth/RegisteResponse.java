package shaddai.backend.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteResponse {

    private Long id;
    private String username;
    private String nombre;
    private String apellido;
    private boolean activo;
}
