package ut.edu.project_skincarebooking.controllers_for_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationRequest;
import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationResponse;
import ut.edu.project_skincarebooking.dto.AuthDTO.RegisterRequest;
import ut.edu.project_skincarebooking.services.interF.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
