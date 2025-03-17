package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
