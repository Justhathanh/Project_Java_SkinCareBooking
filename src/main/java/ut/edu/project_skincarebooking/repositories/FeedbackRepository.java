package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.Feedback;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByAppointmentId(Long appointmentId);

}
