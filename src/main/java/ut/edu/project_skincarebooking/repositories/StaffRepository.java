package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
