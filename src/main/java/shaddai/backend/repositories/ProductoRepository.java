package shaddai.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import shaddai.backend.entities.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>, JpaSpecificationExecutor<ProductoEntity> {
}
