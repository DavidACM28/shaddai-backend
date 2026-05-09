package shaddai.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shaddai.backend.utils.Accion;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auditoria_inventario")
public class AuditoriaInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Enumerated(EnumType.STRING)
    private Accion accion;

    private int stockAntiguo;
    private int stockNuevo;
    private LocalDate fecha;
    private LocalTime hora;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
    }
}
