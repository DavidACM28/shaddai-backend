package shaddai.backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shaddai.backend.dtos.producto.ProductoMasVendidoDTO;
import shaddai.backend.entities.DetalleVentaEntity;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Long> {

    @Query("""
            SELECT new shaddai.backend.dtos.producto.ProductoMasVendidoDTO(
                p.id,
                p.nombre,
                p.descripcion,
                p.precio,
                p.stock,
                SUM(d.cantidad)
            )
            FROM DetalleVentaEntity d
            JOIN d.producto p
            GROUP BY p.id, p.nombre, p.descripcion, p.precio, p.stock
            ORDER BY SUM(d.cantidad) DESC
            """)
    List<ProductoMasVendidoDTO> findProductosMasVendidos(Pageable pageable);
}
