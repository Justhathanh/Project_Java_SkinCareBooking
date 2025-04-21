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
public class AppointmentUpdateRequest {
    private Long customerId;
    private Long therapistId;
    private Long serviceId;
    private LocalDate date;
    private LocalTime time;
    private String status;
}