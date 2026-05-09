package shaddai.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venta")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    private LocalDate fecha;
    private LocalTime hora;
    private double total;
    private String cliente;
    private String medioPago;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
    }
}
