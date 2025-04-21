package ut.edu.project_skincarebooking.services.ImpleM;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationRequest;
import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationResponse;
import ut.edu.project_skincarebooking.dto.AuthDTO.RegisterRequest;
import ut.edu.project_skincarebooking.models.Customer;
import ut.edu.project_skincarebooking.models.Role;
import ut.edu.project_skincarebooking.models.User;
import ut.edu.project_skincarebooking.repositories.UserRepository;
import ut.edu.project_skincarebooking.repositories.CustomerRepository;

import ut.edu.project_skincarebooking.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ut.edu.project_skincarebooking.services.interF.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        // First check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại");
        }

        // Create and save user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        User savedUser = userRepository.save(user);

        // Create Customer if role is CUSTOMER
        if (request.getRole() == Role.CUSTOMER) {
            // Sử dụng constructor thay vì builder để tránh lỗi với các trường null
            Customer customer = new Customer();
            customer.setLoyaltyPoints(0);
            customer.setPhone(null);  // Sử dụng chuỗi rỗng thay vì null
            customer.setDateOfBirth(null);  // Sử dụng chuỗi rỗng thay vì null
            customer.setUser(savedUser);
            customer.setEmail(request.getEmail());



            customerRepository.save(customer);
        }

        // Generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwtToken = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(jwtToken);
    }
}