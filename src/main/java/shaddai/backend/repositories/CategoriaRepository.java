package shaddai.backend.repositories;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shaddai.backend.entities.CategoriaEntity;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    boolean existsByNombre(String nombre);

    Optional<CategoriaEntity> findByNombre(String nombre);
}
