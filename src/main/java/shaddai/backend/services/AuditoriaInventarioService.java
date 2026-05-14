package shaddai.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shaddai.backend.dtos.UsuarioDTO;
import shaddai.backend.dtos.auditoria.AuditoriaInventarioResponse;
import shaddai.backend.dtos.auditoria.CrearAuditoriaInventarioDTO;
import shaddai.backend.dtos.categoria.CategoriaResponse;
import shaddai.backend.dtos.producto.ProductoResponse;
import shaddai.backend.entities.AuditoriaInventarioEntity;
import shaddai.backend.entities.ProductoEntity;
import shaddai.backend.entities.UsuarioEntity;
import shaddai.backend.exceptions.InvalidActionException;
import shaddai.backend.exceptions.ProductNotFoundException;
import shaddai.backend.exceptions.UserNotFoundException;
import shaddai.backend.repositories.AuditoriaInventarioRepository;
import shaddai.backend.repositories.ProductoRepository;
import shaddai.backend.repositories.UsuarioRepository;
import shaddai.backend.utils.Accion;

@Service
public class AuditoriaInventarioService {

    private final AuditoriaInventarioRepository auditoriaInventarioRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public AuditoriaInventarioService(AuditoriaInventarioRepository auditoriaInventarioRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {
        this.auditoriaInventarioRepository = auditoriaInventarioRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public AuditoriaInventarioResponse crear(CrearAuditoriaInventarioDTO dto) {
        ProductoEntity producto = productoRepository.findById(dto.getProductoId()).
                orElseThrow(() -> new ProductNotFoundException(dto.getProductoId().toString()));
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId()).
                orElseThrow(() -> new UserNotFoundException(dto.getUsuarioId().toString()));
        Accion accion;
        try {
             accion = Accion.valueOf(dto.getAccion().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidActionException(dto.getAccion());
        }

        AuditoriaInventarioEntity auditoria = new AuditoriaInventarioEntity();
        auditoria.setProducto(producto);
        auditoria.setUsuario(usuario);
        auditoria.setAccion(accion);
        auditoria.setMensaje(dto.getMensaje());
        auditoria.setStockAntiguo(dto.getStockAntiguo());
        auditoria.setStockNuevo(dto.getStockNuevo());

        return toResponse(auditoriaInventarioRepository.save(auditoria));
    }

    private AuditoriaInventarioResponse toResponse(AuditoriaInventarioEntity auditoria) {
        CategoriaResponse categoria =
                new CategoriaResponse(
                        auditoria.getProducto().getCategoria().getId(),
                        auditoria.getProducto().getCategoria().getNombre(),
                        auditoria.getProducto().getCategoria().isActivo());

        ProductoResponse producto =
                new ProductoResponse(
                        auditoria.getProducto().getId(),
                        categoria,
                        auditoria.getProducto().getNombre(),
                        auditoria.getProducto().getDescripcion(),
                        auditoria.getProducto().getPrecio(),
                        auditoria.getProducto().getStock(),
                        auditoria.getProducto().isActivo());

        UsuarioDTO usuario =
                new UsuarioDTO(
                        auditoria.getUsuario().getId(),
                        auditoria.getUsuario().getUsername(),
                        auditoria.getUsuario().getNombre(),
                        auditoria.getUsuario().getApellido(),
                        auditoria.getUsuario().isActivo());
        return new AuditoriaInventarioResponse(
                auditoria.getId(),
                producto,
                usuario,
                auditoria.getAccion(),
                auditoria.getMensaje(),
                auditoria.getStockAntiguo(),
                auditoria.getStockNuevo(),
                auditoria.getFecha(),
                auditoria.getHora());
    }
}
