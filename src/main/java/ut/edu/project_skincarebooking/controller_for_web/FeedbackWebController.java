package ut.edu.project_skincarebooking.controller_for_web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.Appointment;
import ut.edu.project_skincarebooking.models.Feedback;
import ut.edu.project_skincarebooking.models.ServiceEntity;
import ut.edu.project_skincarebooking.services.interF.AppointmentService;
import ut.edu.project_skincarebooking.services.interF.FeedbackService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackWebController {

    private final AppointmentService appointmentService;
    private final FeedbackService feedbackService;

    @GetMapping
    public String showFeedbackForm(@RequestParam("appointmentId") Long appointmentId, Model model) {
        Appointment appointment = appointmentService.getAppointmentWithServices(appointmentId);
        Feedback feedback = appointment.getFeedback();
        model.addAttribute("appointment", appointment);
        model.addAttribute("feedback", feedback);
        return "feedback";  // ✅ Chỉ trả về feedback.html
    }

    @PostMapping("/submit")
    public String submitFeedback(@RequestParam("appointmentId") Long appointmentId,
                                 @RequestParam("rating") int rating,
                                 @RequestParam("comment") String comment) {

        Appointment appointment = appointmentService.getAppointmentWithServices(appointmentId);

        Feedback feedback = appointment.getFeedback();
        if (feedback == null) {
            // Nếu chưa có feedback thì tạo mới
            ServiceEntity firstService = appointment.getServices().isEmpty() ? null : appointment.getServices().get(0);
            feedback = Feedback.builder()
                    .appointment(appointment)
                    .customer(appointment.getCustomer())
                    .therapist(appointment.getTherapist())
                    .service(firstService)
                    .createdAt(LocalDateTime.now())
                    .build();
        }

        // Cập nhật nội dung feedback
        feedback.setRating(rating);
        feedback.setComment(comment);

        feedbackService.createFeedback(feedback);

        return "redirect:/history";  // ✅ Sau submit luôn quay về trang lịch sử
    }
}
