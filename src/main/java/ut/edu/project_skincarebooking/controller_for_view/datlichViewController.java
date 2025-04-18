package ut.edu.project_skincarebooking.controller_for_view;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/datlich")
public class datlichViewController {
    @GetMapping("/view")
    public String showServicePage() {return "datlich"; // Trả về file service.html trong thư mục templates
    }
}
