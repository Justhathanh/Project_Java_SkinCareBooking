package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.models.Rating;
import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getAllRatings();
    Rating getRatingById(Long id);
    void deleteRating(Long id);
}
