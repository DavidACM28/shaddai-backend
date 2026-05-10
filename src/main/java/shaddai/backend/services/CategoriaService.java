package shaddai.backend.services;

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
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        if (categoriaRepository.existsByNombre(dto.getNombre()) && !dto.getNombre().equals(categoria.getNombre())) {
            throw new CategoryAlreadyExistsException(dto.getNombre());
        }
        categoria.setNombre(dto.getNombre());
        categoria.setActivo(dto.isActivo());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaResponse(categoria.getId(), categoria.getNombre(), categoria.isActivo());
    }

    @Transactional(readOnly = true)
    public List<CategoriaEntity> findAll() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CategoriaEntity> findAllActivas() {
        return categoriaRepository.findByActivo(true);
    }

    @Transactional(readOnly = true)
    public List<CategoriaEntity> findAllInactivas() {
        return categoriaRepository.findByActivo(false);
    }
}
