package ut.edu.project_skincarebooking.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUpdateRequest {
    private String serviceName;
    private String description;
    private double price;
    private int duration;
}