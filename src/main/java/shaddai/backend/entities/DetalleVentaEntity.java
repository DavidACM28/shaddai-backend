package shaddai.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalle_venta")
public class DetalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private VentaEntity venta;

    private int cantidad;
    private double precio;
}
