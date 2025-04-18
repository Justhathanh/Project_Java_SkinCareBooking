package ut.edu.project_skincarebooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.project_skincarebooking.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
