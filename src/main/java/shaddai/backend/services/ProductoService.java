package shaddai.backend.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shaddai.backend.dtos.producto.CrearProductoDTO;
import shaddai.backend.dtos.producto.EditarProductoDTO;
import shaddai.backend.dtos.producto.ProductoMasVendidoDTO;
import shaddai.backend.entities.CategoriaEntity;
import shaddai.backend.entities.ProductoEntity;
import shaddai.backend.exceptions.CategoryNotFoundException;
import shaddai.backend.exceptions.ProductNotFoundException;
import shaddai.backend.repositories.CategoriaRepository;
import shaddai.backend.repositories.DetalleVentaRepository;
import shaddai.backend.repositories.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
                           DetalleVentaRepository detalleVentaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Transactional
    public ProductoEntity crear(CrearProductoDTO dto) {
        CategoriaEntity categoria = new CategoriaEntity(3L, "Sin categoria", true);
        if (dto.getCategoria() != null) {
            categoria = categoriaRepository.findById(dto.getCategoria().getId()).orElseThrow(
                    () -> new CategoryNotFoundException(dto.getCategoria().getId().toString()));
        }

        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria((categoria));
        producto.setStock((dto.getStock() == null ? 0 : dto.getStock()));
        producto.setActivo(true);
        return productoRepository.save(producto);
    }

    @Transactional
    public ProductoEntity editar(Long id, EditarProductoDTO dto) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
        CategoriaEntity categoria = new CategoriaEntity(3L, "Sin categoria", true);
        if (dto.getCategoria() != null) {
            categoria = categoriaRepository.findById(dto.getCategoria().getId()).orElseThrow(
                    () -> new CategoryNotFoundException(dto.getCategoria().getId().toString()));
        }
        producto.setCategoria(categoria);
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setActivo(dto.getActivo());
        return productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    public List<ProductoEntity> findByFilters(Long productoId, Long categoriaId, String nombre, Boolean activo) {
        Specification<ProductoEntity> specification = Specification.where((root, query, cb) -> cb.conjunction());

        if (productoId != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("id"), productoId));
        }
        if (categoriaId != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("categoria").get("id"), categoriaId));
        }
        if (nombre != null) {
            specification = specification.and((root, query, cb) -> cb.like(root.get("nombre"), "%" + nombre + "%"));
        }
        if (activo != null) {
            specification = specification.and((root, query, cb) -> cb.equal(root.get("activo"), activo));
        }

        return productoRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public List<ProductoMasVendidoDTO> findProductosMasVendidos() {
        return detalleVentaRepository.findProductosMasVendidos(PageRequest.of(0, 6));
    }

    @Transactional
    public ProductoEntity activar(Long id) {
        ProductoEntity producto = productoRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
        producto.setActivo(true);
        return productoRepository.save(producto);
    }

    @Transactional
    public ProductoEntity desactivar(Long id) {
        ProductoEntity producto = productoRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
        producto.setActivo(false);
        return productoRepository.save(producto);
    }

}
