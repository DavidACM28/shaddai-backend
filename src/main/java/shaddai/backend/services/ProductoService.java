package shaddai.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shaddai.backend.dtos.producto.CrearProductoDTO;
import shaddai.backend.dtos.producto.EditarProductoDTO;
import shaddai.backend.entities.CategoriaEntity;
import shaddai.backend.entities.ProductoEntity;
import shaddai.backend.exceptions.CategoryNotFoundException;
import shaddai.backend.exceptions.ProductNotFoundException;
import shaddai.backend.repositories.CategoriaRepository;
import shaddai.backend.repositories.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ProductoEntity crear(CrearProductoDTO dto) {
        CategoriaEntity categoria = null;
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
        CategoriaEntity categoria = null;
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
}
