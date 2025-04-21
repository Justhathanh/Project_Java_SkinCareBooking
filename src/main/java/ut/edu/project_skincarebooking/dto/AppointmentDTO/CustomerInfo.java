package ut.edu.project_skincarebooking.dto.AppointmentDTO;

import lombok.Data;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {
    private Long id;
    private String username;
    private String email;
}
