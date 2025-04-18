package ut.edu.project_skincarebooking.services.ImpleM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.models.Feedback;
import ut.edu.project_skincarebooking.repositories.AppointmentRepository;
import ut.edu.project_skincarebooking.repositories.FeedbackRepository;
import ut.edu.project_skincarebooking.services.interF.FeedbackService;

import java.time.LocalDateTime;
import java.util.*;
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final AppointmentRepository appointmentRepository;  // Thêm repository cho Appointment

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
    }


    @Override
    public Feedback createFeedback(Feedback feedback) {
        feedback.setCreatedAt(LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedback) {
        Feedback existing = getFeedbackById(id);
        existing.setRating(feedback.getRating());
        existing.setComment(feedback.getComment());
        return feedbackRepository.save(existing);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
