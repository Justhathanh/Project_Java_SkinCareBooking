package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationRequest;
import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationResponse;
import ut.edu.project_skincarebooking.dto.AuthDTO.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
