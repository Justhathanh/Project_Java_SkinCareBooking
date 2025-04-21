package ut.edu.project_skincarebooking.dto.AppointmentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDetailsResponse {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String status;
    private CustomerInfo customer;
    private TherapistInfo therapist;
    private ServiceInfo service;
    private FeedbackInfo feedback;
    private RatingInfo rating;
}