package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
