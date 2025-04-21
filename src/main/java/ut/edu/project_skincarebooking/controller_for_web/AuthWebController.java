package ut.edu.project_skincarebooking.controller_for_web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationRequest;
import ut.edu.project_skincarebooking.dto.AuthDTO.AuthenticationResponse;
import ut.edu.project_skincarebooking.dto.AuthDTO.RegisterRequest;
import ut.edu.project_skincarebooking.models.Role;
import ut.edu.project_skincarebooking.models.User;
import ut.edu.project_skincarebooking.repositories.UserRepository;
import ut.edu.project_skincarebooking.services.interF.AuthService;
import ut.edu.project_skincarebooking.services.interF.CustomerService;
import ut.edu.project_skincarebooking.models.Customer;

@Controller
@RequiredArgsConstructor
public class AuthWebController {

    private final CustomerService customerService;
    private final UserRepository userRepository;
    private final AuthService authService;

    // ---------- View Pages ----------
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/index")
    public String homePage() {
        return "index";
    }

    // Redirect from root to index
    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }





    @GetMapping("/chuyenvien")
    public String chuyenvien() {
        return "chuyenvien";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/db_report")
    public String dbReport() {
        return "db_report";
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz";
    }

    // ---------- Form Handling ----------
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam(required = false) String email,
            HttpServletResponse response,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {
            Role selectedRole = Role.valueOf(role.toUpperCase());
            RegisterRequest request = new RegisterRequest(username, password, selectedRole, email);

            AuthenticationResponse authResponse = authService.register(request);

            // Store token in both cookie and session for flexibility
            Cookie jwtCookie = new Cookie("token", authResponse.getToken());
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(3600);
            response.addCookie(jwtCookie);

            session.setAttribute("token", authResponse.getToken());
            session.setAttribute("username", username);

            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công!");
            return "redirect:/index";
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi chi tiết vào console để debug
            model.addAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "register";
        }
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {
            AuthenticationRequest request = new AuthenticationRequest(username, password);
            AuthenticationResponse authResponse = authService.authenticate(request);

            // Store token in both cookie and session for flexibility
            Cookie jwtCookie = new Cookie("token", authResponse.getToken());
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(3600);
            response.addCookie(jwtCookie);

            session.setAttribute("token", authResponse.getToken());
            session.setAttribute("username", username);

            // Add this line to store user role in session
            User user = ((UserRepository) authService).findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            session.setAttribute("userRole", user.getRole().toString());
            if (user.getRole() == Role.CUSTOMER) {
                Customer customer = customerService.getCustomerByUser(user);
                session.setAttribute("customerId", customer.getId());
            }

            redirectAttributes.addFlashAttribute("successMessage", "Đăng nhập thành công!");
            return "redirect:/index";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Đăng nhập thất bại: " + e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session) {
        // Xóa cookie
        Cookie jwtCookie = new Cookie("token", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);

        // Xóa session
        session.removeAttribute("token");
        session.removeAttribute("username");
        session.invalidate();

        return "redirect:/login";
    }
}
