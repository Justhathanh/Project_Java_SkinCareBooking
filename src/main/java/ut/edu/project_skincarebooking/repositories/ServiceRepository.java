package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
