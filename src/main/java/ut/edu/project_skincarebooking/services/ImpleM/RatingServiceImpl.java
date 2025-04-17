package ut.edu.project_skincarebooking.services.ImpleM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.models.Rating;
import ut.edu.project_skincarebooking.repositories.RatingRepository;
import ut.edu.project_skincarebooking.services.interF.RatingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }
    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
