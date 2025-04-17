package ut.edu.project_skincarebooking.controllers_for_api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Trả về index.html trong thư mục templates/
    }
}
