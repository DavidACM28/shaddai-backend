package shaddai.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shaddai.backend.dtos.categoria.CategoriaResponse;
import shaddai.backend.dtos.categoria.CrearCategoriaDTO;
import shaddai.backend.dtos.categoria.EditarCategoriaDTO;
import shaddai.backend.entities.CategoriaEntity;
import shaddai.backend.exceptions.CategoryAlreadyExistsException;
import shaddai.backend.exceptions.CategoryNotFoundException;
import shaddai.backend.repositories.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaResponse crear(CrearCategoriaDTO dto) {
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new CategoryAlreadyExistsException(dto.getNombre());
        }
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNombre(dto.getNombre());
        categoria.setActivo(true);
        categoria = categoriaRepository.save(categoria);
        return new CategoriaResponse(categoria.getId(), categoria.getNombre(), categoria.isActivo());
    }

    @Transactional
    public CategoriaResponse editar(EditarCategoriaDTO dto, Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id.toString()));
        if (categoriaRepository.existsByNombre(dto.getNombre()) && !dto.getNombre().equals(categoria.getNombre())) {
            throw new CategoryAlreadyExistsException(dto.getNombre());
        }
        categoria.setNombre(dto.getNombre());
        categoria.setActivo(dto.isActivo());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaResponse(categoria.getId(), categoria.getNombre(), categoria.isActivo());
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponse> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponse> findAllActivas() {
        return categoriaRepository.findByActivo(true).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponse> findAllInactivas() {
        return categoriaRepository.findByActivo(false).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoryNotFoundException(id.toString());
        }
        categoriaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<CategoriaResponse> findAllPage(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public CategoriaResponse findById(Long id) {
        return toResponse(categoriaRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id.toString())));
    }

    @Transactional(readOnly = true)
    public CategoriaResponse findByNombre(String nombre) {
        return toResponse(categoriaRepository.findByNombreIgnoreCase(nombre).orElseThrow(() -> new CategoryNotFoundException(nombre)));
    }

    @Transactional
    public CategoriaResponse activar(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id.toString()));
        categoria.setActivo(true);
        return toResponse(categoria);
    }

    @Transactional
    public CategoriaResponse desactivar(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id.toString()));
        categoria.setActivo(false);
        return toResponse(categoria);
    }

    private CategoriaResponse toResponse(CategoriaEntity categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNombre(), categoria.isActivo());
    }
}
