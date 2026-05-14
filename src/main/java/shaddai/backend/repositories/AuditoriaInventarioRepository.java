package shaddai.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import shaddai.backend.entities.AuditoriaInventarioEntity;

@Repository
public interface AuditoriaInventarioRepository extends JpaRepository<AuditoriaInventarioEntity, Long>, JpaSpecificationExecutor<AuditoriaInventarioEntity> {
}
