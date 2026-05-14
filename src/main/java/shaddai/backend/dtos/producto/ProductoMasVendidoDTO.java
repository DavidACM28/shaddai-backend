package shaddai.backend.dtos.producto;

public interface ProductoMasVendidoDTO {
    Long getId();

    String getNombre();

    String getDescripcion();

    double getPrecio();

    int getStock();

    Long getCantidadVendida();
}
