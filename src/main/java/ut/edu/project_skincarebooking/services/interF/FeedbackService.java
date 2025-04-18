package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.models.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    Feedback getFeedbackById(Long id);
    Feedback createFeedback(Feedback feedback);
    Feedback updateFeedback(Long id, Feedback feedback);
    void deleteFeedback(Long id);
}
