package ut.edu.project_skincarebooking.controller_for_view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/datlich")
    public String datlich() {
        return "datlich";
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
}
