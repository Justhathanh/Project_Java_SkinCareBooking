package ut.edu.project_skincarebooking.dto.AuthDTO;

import lombok.*;
import ut.edu.project_skincarebooking.models.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
    private String email;
}
