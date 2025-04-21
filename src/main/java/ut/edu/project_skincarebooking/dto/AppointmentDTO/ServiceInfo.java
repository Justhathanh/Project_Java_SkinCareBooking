package ut.edu.project_skincarebooking.dto.AppointmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfo {
    private Long id;
    private String serviceName;
    private double price;
    private int duration;
}