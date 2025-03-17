package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
