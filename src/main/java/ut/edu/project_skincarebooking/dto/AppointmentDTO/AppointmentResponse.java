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
public class AppointmentResponse {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String status;
    private String customerName;
    private String therapistName;
    private String serviceName;
}
