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
import ut.edu.project_skincarebooking.models.Customer;
import ut.edu.project_skincarebooking.models.Role;
import ut.edu.project_skincarebooking.models.User;
import ut.edu.project_skincarebooking.repositories.UserRepository;
import ut.edu.project_skincarebooking.services.interF.AuthService;
import ut.edu.project_skincarebooking.services.interF.CustomerService;

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

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/chuyenvien")
    public String chuyenvien() {
        return "chuyenvien";
    }


    @GetMapping("/db_report")
    public String dbReport() {
        return "db_report";
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz";
    }

    // ---------- Đăng ký ----------
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

            // ✅ Gán token vào cookie & session
            Cookie jwtCookie = new Cookie("token", authResponse.getToken());
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(3600);
            response.addCookie(jwtCookie);

            session.setAttribute("token", authResponse.getToken());
            session.setAttribute("username", username);

            // ✅ Gán customerId vào session (nếu role là CUSTOMER)
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            session.setAttribute("userRole", user.getRole().toString());

            if (user.getRole() == Role.CUSTOMER) {
                Customer customer;
                try {
                    customer = customerService.getCustomerByUser(user);
                } catch (Exception e) {
                    customer = new Customer();
                    customer.setUser(user);
                    customer = customerService.saveCustomer(customer);
                }
                session.setAttribute("customerId", customer.getId());
                System.out.println("✅ Gán session: customerId = " + customer.getId());
            }

            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công!");
            return "redirect:/index";

        } catch (Exception e) {
            e.printStackTrace(); // Debug lỗi chi tiết
            model.addAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "register";
        }
    }


    // ---------- Đăng nhập ----------
    @PostMapping("/login")
    public String loginUser(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {
        System.out.println("🚀 ĐÃ GỌI VÀO CONTROLLER /login");

        try {
            AuthenticationRequest request = new AuthenticationRequest(username, password);
            AuthenticationResponse authResponse = authService.authenticate(request);

            // Lưu token và session
            Cookie jwtCookie = new Cookie("token", authResponse.getToken());
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(3600);
            response.addCookie(jwtCookie);

            session.setAttribute("token", authResponse.getToken());
            session.setAttribute("username", username);

            // Lấy User
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            session.setAttribute("userRole", user.getRole().toString());

            // 👉 Gán customerId (nếu chưa có thì tạo)
            if (user.getRole() == Role.CUSTOMER) {
                Customer customer;
                try {
                    customer = customerService.getCustomerByUser(user);
                } catch (Exception e) {
                    // Nếu chưa có, thì tạo mới
                    customer = new Customer();
                    customer.setUser(user);
                    customer = customerService.saveCustomer(customer);
                    System.out.println("🆕 Tạo mới customer cho user: " + username);
                }

                session.setAttribute("customerId", customer.getId());
                System.out.println("✅ Gán session: customerId = " + customer.getId());
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
        Cookie jwtCookie = new Cookie("token", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);

        session.invalidate();

        return "redirect:/login";
    }
}
