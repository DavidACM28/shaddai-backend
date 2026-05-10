package shaddai.backend.services;

import org.springframework.stereotype.Service;
import shaddai.backend.dtos.categoria.CrearCategoriaDTO;
import shaddai.backend.dtos.categoria.CrearCategoriaResponse;
import shaddai.backend.entities.CategoriaEntity;
import shaddai.backend.exceptions.CategoryAlreadyExistsException;
import shaddai.backend.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CrearCategoriaResponse crear(CrearCategoriaDTO dto) {
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new CategoryAlreadyExistsException(dto.getNombre());
        }
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNombre(dto.getNombre());
        categoria.setActivo(true);
        categoria = categoriaRepository.save(categoria);
        return new CrearCategoriaResponse(categoria.getId(), categoria.getNombre(), categoria.isActivo());
    }
}
